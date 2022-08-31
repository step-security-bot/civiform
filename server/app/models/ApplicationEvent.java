package models;

import static com.google.common.base.Preconditions.checkNotNull;

import io.ebean.annotation.DbJson;
import io.ebean.annotation.WhenCreated;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.data.validation.Constraints;
import services.application.ApplicationEventDetails;

@Entity
@Table(name = "application_events")
public final class ApplicationEvent extends BaseModel {

  // The Application the event is on.
  @ManyToOne private Application application;
  // The {@code ApplicationEventDetails.Type} of the event.
  @Constraints.Required private ApplicationEventDetails.Type eventType;
  // The Account that triggered the event.
  @ManyToOne
  @JoinColumn(name = "creator_id")
  private Account creator;
  // Details of the event specific to the eventType.
  @Constraints.Required @DbJson private ApplicationEventDetails details;
  @WhenCreated private Instant createTime;

  /**
   * Creates a representation of a single event happening to an Application.
   *
   * @param creator the Account that created the event.
   */
  public ApplicationEvent(
      Application application,
      Account creator,
      ApplicationEventDetails.Type eventType,
      ApplicationEventDetails details) {
    this.application = checkNotNull(application);
    this.creator = checkNotNull(creator);
    this.eventType = checkNotNull(eventType);
    this.details = checkNotNull(details);
  }

  public Application getApplication() {
    return application;
  }

  public ApplicationEvent setApplication(Application application) {
    this.application = checkNotNull(application);
    return this;
  }

  public ApplicationEventDetails.Type getEventType() {
    return eventType;
  }

  public ApplicationEvent setEventType(ApplicationEventDetails.Type eventType) {
    this.eventType = checkNotNull(eventType);
    return this;
  }

  public Account getCreator() {
    return creator;
  }

  public ApplicationEvent setCreator(Account creator) {
    this.creator = checkNotNull(creator);
    return this;
  }

  public ApplicationEventDetails getDetails() {
    return details;
  }

  public ApplicationEvent setDetails(ApplicationEventDetails details) {
    this.details = checkNotNull(details);
    return this;
  }

  public Instant getCreateTime() {
    return createTime;
  }
}