package com.catalin.javapersistence.repositories.test;

import jakarta.transaction.Transactional;

public interface LogRepositoryCustom {

        void log(String message);

        void logRequireTransaction(String message);

        void showLogs();

        void showLogsSupportsThrowError();

        void showLogsNotSupportedThrowError();
}
