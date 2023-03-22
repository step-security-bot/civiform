package views.admin.settings;

import java.util.Optional;
import play.mvc.Http;
import play.twirl.api.Content;
import services.TranslationLocales;
import views.admin.TranslationFormView;
import javax.inject.Inject;
import java.util.Locale;
import views.HtmlBundle;

public final class SettingsTranslationView extends TranslationFormView {

  @Inject
  public SettingsTranslationView(TranslationLocales translationLocales) {
    super(translationLocales);
  }

  public Content render(Http.Request request, Locale locale) {
    return null;
  }


  @Override
  protected String languageLinkDestination(long entityId, Locale locale) {
    return "";
  }


}
