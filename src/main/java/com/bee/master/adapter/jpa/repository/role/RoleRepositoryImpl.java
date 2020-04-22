package com.bee.master.adapter.jpa.repository.role;

import com.bee.master.adapter.jpa.entity.RolePO;
import com.bee.master.adapter.jpa.repository.RoleJpaRepository;
import com.bee.master.application.component.PageHelper;
import com.bee.master.application.vo.PageVO;
import com.bee.master.application.vo.RoleVO;
import com.bee.master.common.mapper.RoleMapper;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bee.master.application.vo.PageVO.META_KEY_PAGE_INDEX;
import static com.bee.master.application.vo.PageVO.META_KEY_PAGE_SIZE;
import static com.bee.master.application.vo.PageVO.META_KEY_TOTAL_PAGE;
import static com.bee.master.application.vo.PageVO.META_KEY_TOTAL_SIZE;
import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Repository
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final PageHelper pageHelper;

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public PageVO<RoleVO> page(Integer pageIndex, Integer pageSize, String sort, String filterTitle) {
        Pageable pageable = pageHelper.getPageable(pageIndex, pageSize, sort);
        Specification<RolePO> specification = getSpecification(filterTitle);
        Page<RolePO> rolePOPage = roleJpaRepository.findAll(specification, pageable);
        List<RoleVO> roleVOs = rolePOPage.getContent().stream().map(RoleMapper.INSTANCE::entityToVo).collect(toList());
        Map<String, Object> metas = ImmutableMap.of(META_KEY_PAGE_INDEX, rolePOPage.getNumber(),
                META_KEY_PAGE_SIZE, rolePOPage.getSize(),
                META_KEY_TOTAL_PAGE, rolePOPage.getTotalPages(),
                META_KEY_TOTAL_SIZE, rolePOPage.getTotalElements());
        return PageVO.<RoleVO>builder()
                .meta(metas)
                .data(roleVOs)
                .build();
    }

    private Specification<RolePO> getSpecification(String title) {
        return (Specification<RolePO>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (isNotBlank(title)) {
                predicates.add(query.where(criteriaBuilder.like(root.get("title").as(String.class), "%" + title + "%")).getRestriction());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
