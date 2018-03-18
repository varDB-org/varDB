package org.vardb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.vardb.model.entity.Ortholog;

@Repository
public interface OrthologRepository extends JpaRepository< Ortholog, Integer >,
                                            QueryDslPredicateExecutor< Ortholog > {
}
