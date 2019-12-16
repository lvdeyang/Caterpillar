package com.sumavision.tetris.outlink;


public class myUrl {
    public static final String HOST="";
    //获取全部考勤记录
    public static final String GETALLREC="/cs/attendRec/sel";
    //删除员工权限
    public static final String DELAUTH="  /cs/passAuth/del";
    //删除全部员工权限
    public static final String DELALLAUTH="/cs/passAuth/delList";
    //添加员工权限
    public static final String ADDAUTH=" /cs/passAuth/add";
    //批量添加员工权限
    public static final String ADDALLAUTH="/cs/passAuth/addList";
    //获取员工权限
    public static final String GETWORKERAUTH="/cs/passAuth/deviceList";
    //修改员工信息
    public static final String UPDATEINFO="/cs/worker/mod";
    //删除员工信息
	public static final String DELETEINFO ="/cs/worker/del";
	//添加员工信息
	public static final String ADDINFO = "/cs/worker/add";

}
