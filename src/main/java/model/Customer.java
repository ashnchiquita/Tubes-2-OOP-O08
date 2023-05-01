package model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
public class Customer implements Serializable {
  private final int id;
}
