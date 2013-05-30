package com.ejb.eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Address;
import model.Groups;
import model.User;

import com.beans.RegistrationBean;

/**
 * Session Bean implementation class RegistrationEAO
 */
@Stateless
@LocalBean
public class RegistrationEAO {

    @PersistenceContext()
    EntityManager entityManager;

    public RegistrationEAO() {
    }

    public boolean persistUser(RegistrationBean registrationBean) {
        try {
            User user = new User();
            user.setName(registrationBean.getUsername());
            user.setForname(registrationBean.getUserforname());
            user.setPassword(com.utils.Util.hash(registrationBean.getPassword()));
            user.setPesel(registrationBean.getPesel());
            user.setEmail(registrationBean.getEmail());
            user.setDateOfBirth(registrationBean.getDob());
            user.setRegisterDate(new java.util.Date());

            Address address = new Address();
            address.setStreet(registrationBean.getStreet());
            address.setBuildingNr(1);//do poprawki
            address.setCity(registrationBean.getCity());
            address.setPostCode(registrationBean.getZip());
            address.setCountry(registrationBean.getCountry());
            address.setState(registrationBean.getProvince());

            user.setAddress(address);
            List<User> users = new ArrayList<User>();
            users.add(user);

            address.setUsers(users);

            //Wyci�gam dane na temat grup z bazy
            String groupname = "admin";

            Query query = this.entityManager.createQuery(
                    "SELECT c FROM Groups c WHERE c.name=?1");
            query.setParameter("1", groupname);

            //List<Groups> groups = com.utils.Util.castList(Groups.class, query.getResultList());
            user.setGroups((Groups) query.getSingleResult());

            entityManager.persist(user);
            return true;
        } catch (ClassCastException e) {
            System.out.println("Class Cast Exception");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument Exception");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Generic Exception");
            e.printStackTrace();
        }
        return false;
    }
}