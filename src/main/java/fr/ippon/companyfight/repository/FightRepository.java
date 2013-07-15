package fr.ippon.companyfight.repository;

import fr.ippon.companyfight.model.Fight;
import fr.ippon.companyfight.model.Organization;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class FightRepository {

    @PersistenceContext
    private EntityManager em;

    public Fight createFight(Organization organization1, Organization organization2) {
        Fight fight = new Fight();
        fight.setFightDate(Calendar.getInstance().getTime());
        fight.setOrganization1(organization1);
        fight.setOrganization2(organization2);
        em.persist(fight);
        return fight;
    }

    public List<Fight> findLatestFights() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Fight> cq = cb.createQuery(Fight.class);
        Root<Fight> Fight = cq.from(Fight.class);
        cq.select(Fight);
        TypedQuery<Fight> q = em.createQuery(cq);
        List<Fight> allFights = q.getResultList();
        return allFights;
    }
}
