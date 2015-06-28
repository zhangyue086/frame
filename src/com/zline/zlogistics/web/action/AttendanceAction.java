package com.zline.zlogistics.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.Attendance;
import com.zline.zlogistics.biz.dal.entity.AttendanceInfo;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.LeaveInfo;
import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.dal.entity.Schedule;
import com.zline.zlogistics.biz.manager.IAttendanceService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.ILeaveInfoService;
import com.zline.zlogistics.biz.manager.IScheduleService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;

public class AttendanceAction extends BaseAction {
	public Logger log = Logger.getLogger(AttendanceAction.class);
	private DataTableReturnObject returnObject;
	private Message message;
	private List<DistributionMember> memberList;
	private List<Schedule> scheduleList;
	private IDistributionMemberService distributionMemberService;
	private IScheduleService scheduleService;
	private IAttendanceService attendanceService;
	private ILeaveInfoService leaveInfoService;
	private Attendance attendance;
	private LeaveInfo leaveInfo;
	private AttendanceInfo attendanceInfo;
	
//	public String initList(){
//		//配送员列表
//		Long distributionStationId = UserContext.getUser().getDistributionStationId();
//		DistributionMember member = new DistributionMember();
//		member.setDistributionStationId(distributionStationId);
//		memberList = distributionMemberService.queryList(member);
//		return "initList";
//	}
	
	public String initList(){
		Object a = this.getRequestParam("s");
		if(a !=null){
			return "initList";
		}
		return "init_list";
	}
	
	public String list(){
		if(attendanceInfo == null){
			attendanceInfo = new AttendanceInfo();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		attendanceInfo.setFirstRow(Integer.parseInt(start));
		attendanceInfo.setPageRows(Integer.parseInt(length));
		Integer count = attendanceService.queryAttendanceInfoCount(attendanceInfo);
		List<AttendanceInfo> list = attendanceService.queryAttendanceInfo(attendanceInfo);
		returnObject = new DataTableReturnObject<AttendanceInfo>();
		returnObject.setData(list);
		returnObject.setRecordsTotal(count);
		returnObject.setRecordsFiltered(count);
		returnObject.setDraw(Integer.parseInt(getRequest().getParameter("draw") == null ? "0"
				: getRequest().getParameter("draw")) + 1);
		return "list";
	}
	
	/**
	 * 通过配送员查询其所排班次
	 * @return
	 */
	public String queryScheduleByMember(){
		MemberSchedule ms = new MemberSchedule();
		String id = getRequest().getParameter("distributionMemberId");
		ms.setDistributionMemberId(Long.valueOf(id));
		ms.setScheduleDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		scheduleList = scheduleService.queryScheduleForMember(ms);
		return "scheduleList";
	}
	
	/**
	 * 打卡
	 * @return
	 */
	public String pushCard(){
		message = new Message();
		message.setIsSuccess(true);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		attendance.setAttendanceDate(formatDate.format(new Date()));
		//查询该班次是否已经有打卡记录，以及打卡次数
		List<Attendance> list = attendanceService.queryList(attendance);
		try {
			if (list == null || list.isEmpty()) {
				//打上班卡
				attendance.setStartWorkTime(formatTime.format(new Date()));
				attendanceService.saveAttendance(attendance);
				message.setInfo("上班打卡成功");
			}else{
				//打下班卡
				Attendance att = list.get(0);
				att.setEndWorkTime(formatTime.format(new Date()));
				attendanceService.updateAttendancee(att);
				message.setInfo("下班打卡成功");
			}
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("打卡失败"+e.getMessage());
		}
		return "pushCard";
	}
	
	/**
	 * 加班
	 * @return
	 */
	public String overTime(){
		message = new Message();
		message.setIsSuccess(true);
		String workType = getRequest().getParameter("workType");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
		attendance.setAttendanceDate(formatDate.format(new Date()));
		attendance.setScheduleId(-1l);
		//查询该班次是否已经有打卡记录，以及打卡次数
		List<Attendance> list = attendanceService.queryList(attendance);
		try {
			if (workType.equals("1")) {
				//打加班上班卡
				attendance.setStartWorkTime(formatTime.format(new Date()));
				attendanceService.saveAttendance(attendance);
				message.setInfo("加班上班打卡成功");
			}
			if (workType.equals("2")) {
				if(list == null || list.isEmpty()){
					message.setIsSuccess(false);
					message.setInfo("没有打加班上班卡");
					return "overTime";
				}
				//打下班卡
				Attendance att = list.get(list.size()-1);
				att.setEndWorkTime(formatTime.format(new Date()));
				attendanceService.updateAttendancee(att);
				message.setInfo("加班下班打卡成功");
			}
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("加班打卡失败"+e.getMessage());
		}
		return "overTime";
	}
	
	public String leaveAdd(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			leaveInfoService.saveLeaveInfo(leaveInfo);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("请假失败"+e.getCause());
		}
		return "leaveAdd";
	}

	public DataTableReturnObject getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(DataTableReturnObject returnObject) {
		this.returnObject = returnObject;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public List<DistributionMember> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<DistributionMember> memberList) {
		this.memberList = memberList;
	}
	public IDistributionMemberService getDistributionMemberService() {
		return distributionMemberService;
	}
	public void setDistributionMemberService(IDistributionMemberService distributionMemberService) {
		this.distributionMemberService = distributionMemberService;
	}
	public IScheduleService getScheduleService() {
		return scheduleService;
	}
	public void setScheduleService(IScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public IAttendanceService getAttendanceService() {
		return attendanceService;
	}

	public void setAttendanceService(IAttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	public LeaveInfo getLeaveInfo() {
		return leaveInfo;
	}

	public void setLeaveInfo(LeaveInfo leaveInfo) {
		this.leaveInfo = leaveInfo;
	}

	public ILeaveInfoService getLeaveInfoService() {
		return leaveInfoService;
	}

	public void setLeaveInfoService(ILeaveInfoService leaveInfoService) {
		this.leaveInfoService = leaveInfoService;
	}

	public AttendanceInfo getAttendanceInfo() {
		return attendanceInfo;
	}

	public void setAttendanceInfo(AttendanceInfo attendanceInfo) {
		this.attendanceInfo = attendanceInfo;
	}
	
}
