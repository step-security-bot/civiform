package featureflags;

import static com.google.common.base.Preconditions.checkNotNull;
import static featureflags.FeatureFlag.admin_reporting_ui_enabled;
import static featureflags.FeatureFlag.allow_civiform_admin_access_programs;
import static featureflags.FeatureFlag.esri_address_correction_enabled;
import static featureflags.FeatureFlag.esri_address_service_area_validation_enabled;
import static featureflags.FeatureFlag.intake_form_enabled;
import static featureflags.FeatureFlag.nongated_eligibility_enabled;
import static featureflags.FeatureFlag.phone_question_type_enabled;
import static featureflags.FeatureFlag.program_eligibility_conditions_enabled;
import static featureflags.FeatureFlag.program_read_only_view_enabled;
import static featureflags.FeatureFlag.show_civiform_image_tag_on_landing_page;

import com.typesafe.config.Config;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Http.Request;

/**
 * Provides configuration backed values that indicate if application wide features are enabled.
 *
 * <p>Values are primarily derived from {@link Config} with overrides allowed via the {@link
 * Request} session cookie as set by {@link controllers.dev.FeatureFlagOverrideController}.
 */
public final class FeatureFlags {
  private static final Logger logger = LoggerFactory.getLogger(FeatureFlags.class);
  private final Config config;

  @Inject
  FeatureFlags(Config config) {
    this.config = checkNotNull(config);
  }

  public boolean areOverridesEnabled() {
    return config.hasPath(FeatureFlag.feature_flag_overrides_enabled.name())
        && config.getBoolean(FeatureFlag.feature_flag_overrides_enabled.name());
  }

  /**
   * If the Eligibility Conditions feature is enabled.
   *
   * <p>Allows for overrides set in {@code request}.
   */
  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isProgramEligibilityConditionsEnabled(Request request) {
    return getFlagEnabled(request, program_eligibility_conditions_enabled);
  }

  /** If the Eligibility Conditions feature is enabled in the system configuration. */
  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isProgramEligibilityConditionsEnabled() {
    return config.getBoolean(program_eligibility_conditions_enabled.name());
  }

  /** If the reporting view in the admin UI is enabled */
  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isAdminReportingUiEnabled() {
    return config.getBoolean(admin_reporting_ui_enabled.name());
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean allowCiviformAdminAccessPrograms(Request request) {
    return getFlagEnabled(request, allow_civiform_admin_access_programs);
  }

  /**
   * If the CiviForm image tag is show on the landing page.
   *
   * <p>Allows for overrides set in {@code request}.
   */
  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean showCiviformImageTagOnLandingPage(Request request) {
    return getFlagEnabled(request, show_civiform_image_tag_on_landing_page);
  }

  // If the UI can show a read only view of a program. Without this flag the
  // only way to view a program is to start editing it.
  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isReadOnlyProgramViewEnabled() {
    return config.getBoolean(program_read_only_view_enabled.name());
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isReadOnlyProgramViewEnabled(Request request) {
    return getFlagEnabled(request, program_read_only_view_enabled);
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isEsriAddressCorrectionEnabled(Request request) {
    return getFlagEnabled(request, esri_address_correction_enabled);
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isEsriAddressServiceAreaValidationEnabled(Request request) {
    return getFlagEnabled(request, esri_address_service_area_validation_enabled);
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isIntakeFormEnabled(Request request) {
    return getFlagEnabled(request, intake_form_enabled);
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isNongatedEligibilityEnabled(Request request) {
    return getFlagEnabled(request, nongated_eligibility_enabled);
  }

  // TODO(#4437): remove and have clients call getFlagEnabled directly.
  public boolean isPhoneQuestionTypeEnabled(Request request) {
    return getFlagEnabled(request, phone_question_type_enabled);
  }

  public Map<FeatureFlag, Boolean> getAllFlagsSorted(Request request) {
    Map<FeatureFlag, Boolean> map = new TreeMap<>(Comparator.comparing(FeatureFlag::name));

    for (FeatureFlag flag : FeatureFlag.values()) {
      map.put(flag, getFlagEnabled(request, flag));
    }

    return map;
  }

  /**
   * Returns the current setting for {@code flag} from {@link Config} if present, allowing for an
   * overriden value from the session cookie.
   *
   * <p>Returns false if the value is not present.
   */
  public boolean getFlagEnabled(Request request, FeatureFlag flag) {
    Optional<Boolean> maybeConfigValue = getFlagEnabledFromConfig(flag);
    if (maybeConfigValue.isEmpty()) {
      return false;
    }
    Boolean configValue = maybeConfigValue.get();

    if (!areOverridesEnabled()) {
      return configValue;
    }

    Optional<Boolean> sessionValue = request.session().get(flag.name()).map(Boolean::parseBoolean);
    if (sessionValue.isPresent()) {
      logger.warn("Returning override ({}) for feature flag: {}", sessionValue.get(), flag.name());
      return sessionValue.get();
    }
    return configValue;
  }

  /** Returns the current setting for {@code flag} from {@link Config} if present. */
  public Optional<Boolean> getFlagEnabledFromConfig(FeatureFlag flag) {
    if (!config.hasPath(flag.name())) {
      logger.warn("Feature flag requested for unconfigured flag: {}", flag.name());
      return Optional.empty();
    }
    return Optional.of(config.getBoolean(flag.name()));
  }
}
