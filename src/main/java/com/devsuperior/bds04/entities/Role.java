package com.devsuperior.bds04.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

  private static final long serialVersionUID = 3534551941343497454L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String authority;

  @ManyToMany(mappedBy = "roles")
  private final Set<User> users = new HashSet<>();

  public Role() {}

  public Role(Long id, String authority){
    this.id = id;
    this.authority = authority;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public Set<User> getUsers() {
    return users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role role = (Role) o;
    return id.equals(role.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
