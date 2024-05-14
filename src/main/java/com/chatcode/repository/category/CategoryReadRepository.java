package com.chatcode.repository.category;

import static com.chatcode.jooq.tables.Category.CATEGORY;
import static org.jooq.impl.DSL.max;

import com.chatcode.domain.entity.Category;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryReadRepository implements ReadRepository<Category> {

    private final DSLContext dsl;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Category> findById(long id) {
        List<Category> categories = nativeQuery(em, dsl.select().from(CATEGORY).where(CATEGORY.ID.eq(id)),
                Category.class);
        return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
    }

    public Optional<Category> findByName(String name) {
        List<Category> categories = nativeQuery(em, dsl.select().from(CATEGORY).where(CATEGORY.NAME.eq(name)),
                Category.class);
        return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
    }

    public List<Category> findAll() {
        return nativeQuery(em, dsl.select().from(CATEGORY).orderBy(CATEGORY.SORT_ORDER), Category.class);
    }

    public Integer count() {
        return nativeQuery(em, dsl.selectCount().from(CATEGORY), Integer.class).get(0);
    }
}