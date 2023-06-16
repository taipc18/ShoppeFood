package Main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import Main.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}
