package kr.kein.putwww.repository.jpa;

import kr.kein.putwww.entity.ConfTarget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

public interface ConfTargetRepository extends CrudRepository<ConfTarget, Long> {
    @Query("select tg_id, title, descript, tg_url, regdate, regid from ConfTarget")
    List<ConfTarget> selectConfTarget();

}
