package com.ejb.eao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Address;
import model.User;
import model.GroupLvl;

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
            user.setBanned(0);

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

            Query query = this.entityManager.createQuery("SELECT c FROM GroupLvl c WHERE c.accLevel=1");
            user.setGroupLvl((GroupLvl) query.getSingleResult());

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
    public void setGroupLvl(String newGroupLvl, String userEmail)
    {
        GroupLvl changedGL = new GroupLvl();
        User chUser = (User) entityManager.createQuery("SELECT c FROM User c WHERE c.email = :userEmail").setParameter("userEmail", userEmail).getSingleResult();
        changedGL.setAccLevel((Integer) entityManager.createQuery("SELECT c FROM GroupLvl c WHERE c.name=:newGroupLvl").setParameter("newGroupLvl", newGroupLvl).getSingleResult());
        changedGL.setName(newGroupLvl);
        chUser.setGroupLvl(changedGL);
        entityManager.persist(chUser);
    }
}
