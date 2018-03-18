package org.vardb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.vardb.model.entity.Taxon;

@Repository
public interface TaxonomyRepository extends JpaRepository< Taxon, Integer >,
                                            QueryDslPredicateExecutor< Taxon > {
}
