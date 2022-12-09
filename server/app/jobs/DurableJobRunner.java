package jobs;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.PersistedDurableJobRepository;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**  */
public class DurableJobRunner {
  private static final Logger logger = LoggerFactory.getLogger(DurableJobRunner.class);

  private final Clock clock;
  private final PersistedDurableJobRepository persistedDurableJobRepository;

  @Inject
  public DurableJobRunner(Clock clock, PersistedDurableJobRepository persistedDurableJobRepository) {
    this.clock = Preconditions.checkNotNull(clock);
    this.persistedDurableJobRepository = Preconditions.checkNotNull(persistedDurableJobRepository);
  }

  public void run() {
    logger.info("JobRunner_Start");
    var startTime = Instant.now(clock);

    do {
      persistedDurableJobRepository.getJobForExecution();
      // run the job
    } while (Instant.now(clock).isBefore(startTime.plus(1, ChronoUnit.SECONDS)));

    logger.info("JobRunner_End");
  }
}
