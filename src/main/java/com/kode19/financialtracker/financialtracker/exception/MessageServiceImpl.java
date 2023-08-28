package com.kode19.financialtracker.financialtracker.exception;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageSource messageSource;

  public MessageServiceImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public String dataNotAvailable(String dataName) {
    return messageSource.getMessage("messages.data.not.available",
        new Object[]{dataName.toUpperCase()},
        Locale.getDefault());
  }

  @Override
  public String dataNotFound(String dataName, String dataId) {
    return messageSource.getMessage("messages.data.not.found",
        new Object[]{dataName.toUpperCase(), dataId},
        Locale.getDefault());
  }

  @Override
  public String getMessage(String key) {
    return messageSource.getMessage(
        key,
        null,
        Locale.getDefault());
  }

}
