package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ExTransport.
 */
@Entity
@Table(name = "ex_transport")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExTransport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ex_extransport", nullable = false)
    private Long ex_extransport;

    @NotNull
    @Column(name = "ex_extransporta", nullable = false)
    private Long ex_extransporta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEx_extransport() {
        return ex_extransport;
    }

    public ExTransport ex_extransport(Long ex_extransport) {
        this.ex_extransport = ex_extransport;
        return this;
    }

    public void setEx_extransport(Long ex_extransport) {
        this.ex_extransport = ex_extransport;
    }

    public Long getEx_extransporta() {
        return ex_extransporta;
    }

    public ExTransport ex_extransporta(Long ex_extransporta) {
        this.ex_extransporta = ex_extransporta;
        return this;
    }

    public void setEx_extransporta(Long ex_extransporta) {
        this.ex_extransporta = ex_extransporta;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExTransport exTransport = (ExTransport) o;
        if (exTransport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exTransport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExTransport{" +
            "id=" + getId() +
            ", ex_extransport=" + getEx_extransport() +
            ", ex_extransporta=" + getEx_extransporta() +
            "}";
    }
}
