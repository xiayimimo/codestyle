package com.tycho.codestyle.eight;

/**
 * 八-5 【强制】所有的枚举类型字段必须要有注释，说明每个数据项的用途
 * @author shenjuntao
 * @date 2020年1月3日
 */
public enum OrderStateEnum {

  OPT_CANCEL("0","取消","CANCEL"),
  OPT_CREATE("10","未支付","CREATE"),
  OPT_ONLINE_PAY("20","待发货","ONLINE_PAY"),
  OPT_BUSI_AUDIT("14","业务审核","BUSI_PAY"),
  OPT_FINA_AUDIT("17","财务审核","FINA_AUDIT"),
  OPT_CON_MENT("25","发货中","CON_MENT"),
  OPT_DISM_TING("100","已拆单","DISM_TING"),
  OPT_DISPATCH("30","已发货","DISPATCH"),
  OPT_FINISH("50","已完成","FINISH"),
  OPT_UNKNOWN("-1","未知","UNKNOWN");

  /**
   * 订单状态编码
   */
  private String code;
  /**
   * 订单状态名称
   */
  private String stateName;
  /**
   * 订单状态英文名称
   */
  private String stateEnName;

  /**
   * 构造函数
   * @param code 状态码
   * @param stateName 状态中文名
   * @param stateEnName 状态英文名
   */
  OrderStateEnum(String code, String stateName, String stateEnName) {
      this.code = code;
      this.stateName = stateName;
      this.stateEnName = stateEnName;
  }

  public String getCode() {
      return code;
  }
  public void setCode(String code) {
      this.code = code;
  }
  public String getStateName() {
      return stateName;
  }
  public String getStateEnName() {
      return stateEnName;
  }
  
  
}

