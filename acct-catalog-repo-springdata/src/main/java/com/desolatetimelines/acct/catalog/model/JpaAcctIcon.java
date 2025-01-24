package com.desolatetimelines.acct.catalog.model;

import jakarta.persistence.*;

import java.util.Objects;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIconCategory;
import static java.util.function.Function.identity;

@Entity
@Table(name = "icon")
public class JpaAcctIcon implements AcctIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iconId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "icon_category_id")
    private JpaAcctIconCategory iconCategory;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "icon_uuid")
    private String iconUUID;

    @Column(name = "icon_base64")
    private String iconBytesBase64;

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    @Override
    public String getIconUUID() {
        return iconUUID;
    }

    @Override
    public void setIconUUID(String iconUUID) {
        this.iconUUID = iconUUID;
    }

    @Override
    public String getIconName() {
        return iconName;
    }

    @Override
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public String getIconBytesBase64() {
        return iconBytesBase64;
    }

    @Override
    public void setIconBytesBase64(String iconBytesBase64) {
        this.iconBytesBase64 = iconBytesBase64;
    }

    @Override
    public AcctIconCategory getIconCategory() {
        return iconCategory;
    }

    @Override
    public void setIconCategory(AcctIconCategory category) {
        this.iconCategory = doWithJpaAcctIconCategory(category, identity());
    }

    public void setIconCategory(JpaAcctIconCategory iconCategory) {
        this.iconCategory = iconCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JpaAcctIcon that = (JpaAcctIcon) o;
        return Objects.equals(iconUUID, that.iconUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(iconUUID);
    }
}
