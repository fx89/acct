package com.desolatetimelines.acct.catalog.repository;

import com.desolatetimelines.acct.catalog.model.AcctIcon;
import com.desolatetimelines.acct.catalog.model.JpaAcctIcon;
import com.desolatetimelines.acct.catalog.springrepository.JpaAcctIconsRepository;
import com.desolatetimelines.acct.common.model.Page;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.desolatetimelines.acct.catalog.util.AcctCatalogRepoSpringDataUtils.doWithJpaAcctIcon;
import static com.desolatetimelines.acct.common.model.Page.emptyPage;
import static java.util.function.Function.identity;

/**
 * Implementation of the {@link AcctIconsRepository} that uses Spring Data
 */
@Service
public class SpringJpaAcctIconsRepository implements AcctIconsRepository {

    private final JpaAcctIconsRepository jpaAcctIconsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public SpringJpaAcctIconsRepository(JpaAcctIconsRepository jpaAcctIconsRepository) {
        this.jpaAcctIconsRepository = jpaAcctIconsRepository;
    }

    @Override
    public AcctIcon createNew() {
        return new JpaAcctIcon();
    }

    @Override
    public AcctIcon save(AcctIcon icon) {
        return doWithJpaAcctIcon(icon, jpaAcctIconsRepository::save);
    }

    @Override
    public Long countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern,
        String iconCategoryName
    ) {
        return
            jpaAcctIconsRepository.countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern == null ? null : ("%" + iconNamePattern + "%"),
                iconCategoryName
            );
    }

    @Override
    public Page<AcctIcon> findAllByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
        String iconNamePattern, String iconCategoryName, int pageNumber, int pageSize
    ) {
        // Get the total number of elements
        final Long maxElements =
            countByIconNameLikeOrNameNullAndIconCategoryNameOrIconCategoryNameNull(
                iconNamePattern, iconCategoryName
            );

        // If the total number of elements is zero then return an empty page
        if (maxElements == 0) {
            return emptyPage();
        }

        // If the lage is not empty then fetch its contents
        @SuppressWarnings("unchecked") final List<AcctIcon> pageContent =
            entityManager
                .createQuery("""
                        select
                            i
                        from
                            JpaAcctIcon i
                        where
                              (:iconNamePattern is null or i.iconName like :iconNamePattern)
                          and (:iconCategoryName is null or i.iconCategory.iconCategoryName = :iconCategoryName)
                    """)
                .setMaxResults(pageSize)
                .setFirstResult(pageNumber * pageSize)
                .setParameter("iconNamePattern", "%" + iconNamePattern + "%")
                .setParameter("iconCategoryName", iconCategoryName)
                .getResultList();

        // Build and return the page
        return
            new Page<>(
                pageContent,
                pageContent.size(),
                maxElements
            );
    }

    @Override
    public Optional<AcctIcon> findFirstIconByIconUUID(String iconUUID) {
        return jpaAcctIconsRepository.findFirstByIconUUID(iconUUID).map(identity());
    }

    @Override
    public Collection<AcctIcon> findAllByIconUUIDIn(Collection<String> iconUUIDs) {
        return
            jpaAcctIconsRepository.findAllByIconUUIDIn(iconUUIDs)
                .stream()
                .map(jpaAcctIcon -> (AcctIcon) jpaAcctIcon)
                .toList();

    }

    @Override
    public void delete(Collection<AcctIcon> icons) {
        jpaAcctIconsRepository.deleteAll(
            icons.stream()
                .map(icon -> doWithJpaAcctIcon(icon, identity()))
                .toList()
        );
    }

}
