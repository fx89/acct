package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "icon_category")
public class JpaAcctIconCategory implements AcctIconCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iconCategoryId;

    @Column(name = "icon_category_name")
    private String iconCategoryName;

    public Long getIconCategoryId() {
        return iconCategoryId;
    }

    public void setIconCategoryId(Long iconCategoryId) {
        this.iconCategoryId = iconCategoryId;
    }

    @Override
    public String getIconCategoryName() {
        return iconCategoryName;
    }

    @Override
    public void setIconCategoryName(String iconCategoryName) {
        this.iconCategoryName = iconCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctIconCategory that = (JpaAcctIconCategory) o;
        return Objects.equals(iconCategoryName, that.iconCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(iconCategoryName);
    }
}
