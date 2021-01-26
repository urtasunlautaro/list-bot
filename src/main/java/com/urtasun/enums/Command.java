package com.urtasun.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Command {

  ADD("/agregar"),
  LIST("/lista"),
  DELETE("/borrar"),
  CLEAN("/limpiar");

  private final String text;

  public String getText() {
    return text;
  }

}
