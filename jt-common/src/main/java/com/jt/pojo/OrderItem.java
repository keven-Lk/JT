package com.jt.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
@TableName("tb_order_item")
@Accessors(chain=true)
@Data
public class OrderItem extends BasePojo{
	
	@TableId
    private String itemId;
	
	@TableId	
    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;
}