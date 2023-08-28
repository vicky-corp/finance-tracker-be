package com.kode19.financialtracker.financialtracker.exception;

public interface MessageService {

  String dataNotAvailable(String dataName);

  String dataNotFound(String dataName, String dataId);

  String getMessage(String key);
}
