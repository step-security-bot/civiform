package featureflags;

import com.google.common.base.Ascii;

/**
 * An enum to represent feature flags used in CiviForm.
 *
 * <p>Note that the built-in Enum name() method is used in the codebase to get the key for
 * configuration. This returns the exact enum name defined here. Therefore, it is imperative that
 * enums are defined in lower_camel_case, enforced by the constructor of this class. Note that this
 * is not the in typical Java convention for enum names, but it allows us to have one source of
 * truth for the enum name (as opposed to defining an additional enum attribute for the conf name).
 */
public enum FeatureFlag {
  // Main control for any feature flags working.
  feature_flag_overrides_enabled,

  // Long lived feature flags.
  allow_civiform_admin_access_programs,
  admin_reporting_ui_enabled,
  show_civiform_image_tag_on_landing_page,

  // Launch Flags, these will eventually be removed.
  program_eligibility_conditions_enabled,
  program_read_only_view_enabled,

  // Address correction and verifcation flags
  esri_address_correction_enabled,
  esri_address_service_area_validation_enabled,

  // Common Intake Form flags.
  intake_form_enabled,
  nongated_eligibility_enabled,

  // Phone number question type.
  phone_question_type_enabled;

  FeatureFlag() {
    if (!name().equals(Ascii.toLowerCase(name()))) {
      throw new RuntimeException(
          "Enum name must be lower_underscore in order to match the style of the conf files"
              + "when FeatureFlag::name is called.");
    }
  }

  public static FeatureFlag getByName(String name) {
    for (FeatureFlag flag : values()) {
      if (name.equals(flag.name())) {
        return flag;
      }
    }
    throw new RuntimeException("No flag found");
  }
}
