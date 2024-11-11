package iuh.week01_lab_huynhhoangphuc_21036541.services;


import iuh.week01_lab_huynhhoangphuc_21036541.entities.Log;
import iuh.week01_lab_huynhhoangphuc_21036541.repositories.LogRepository;

import java.time.Instant;


public class LogService {
   private final LogRepository logRepository;

   public LogService() {
      logRepository = new LogRepository();
   }

   public boolean addLog(String accountId) {
      Log log = new Log(
            null,
            accountId,
            Instant.now(),
            Instant.EPOCH,
            "Login successfully"
      );
      return logRepository.add(log);
   }

   public boolean updateLog(String accountId) {
      Log log = logRepository.findByAccountId(accountId);
      log.setLogoutTime(Instant.now());
      return logRepository.update(log);

   }
}
