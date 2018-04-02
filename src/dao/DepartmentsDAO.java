/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Departments;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import tools.HibernateUtil;

/**
 *
 * @author TAMU
 */
public class DepartmentsDAO implements InterfaceDAO{
    public SessionFactory factory;
    public Session session;
    public Transaction transaksi;
    
    public DepartmentsDAO(){
        this.factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public boolean insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object object) {
        boolean flag = false;
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
            Departments departments = (Departments) object;
            Departments dep = (Departments) session.get(Departments.class, departments);
            dep.setDepartmentId(departments.getDepartmentId());
            dep.setDepartmentName(departments.getDepartmentName());
            session.update(dep);
            transaksi.commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
            session.close();
        }
        return flag;
    }

    @Override
    public boolean delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> search(String category, String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getById(String id) {
       Object obj = new Object();
        try {
            session = factory.openSession();
            transaksi = session.beginTransaction();
              obj = session.createQuery("From Departments where department_id=:id")
                      .setParameter("id", Integer.parseInt(id)).uniqueResult();
            transaksi.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaksi!=null)transaksi.rollback();
        }finally{
            session.close();
        }
        return obj;
    }
}