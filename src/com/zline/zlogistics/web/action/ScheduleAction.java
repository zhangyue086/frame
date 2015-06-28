package com.zline.zlogistics.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.EventSource;
import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.dal.entity.Schedule;
import com.zline.zlogistics.biz.dal.entity.ShiftSchedule;
import com.zline.zlogistics.biz.dal.entity.DateSchedule;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.manager.IMemberScheduleService;
import com.zline.zlogistics.biz.manager.IScheduleService;
import com.zline.zlogistics.biz.manager.IShiftScheduleService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.common.DataTableReturnObject;
import com.zline.zlogistics.web.util.DateUtils;
import com.zline.zlogistics.web.view.DateScheduleView;

public class ScheduleAction extends BaseAction
{
	public Logger log = Logger.getLogger(ScheduleAction.class);
	private DataTableReturnObject returnObject;
	private Message message;
	private IScheduleService scheduleService;
	private IMemberScheduleService memberScheduleService;
	private IDistributionStationService distributionStationService;
	private IDistributionMemberService distributionMemberService;
	private List<Schedule> scheduleList;
	private Schedule schedule;
	private ShiftSchedule shift;
	private DistributionStation station;
	private List<EventSource> eventList;
	private List<DistributionMember> memberList;
	private List<ShiftSchedule> shiftList;
	private IShiftScheduleService shiftScheduleService;
	private List<DateScheduleView> scheduleViewList;
	private String today;
	private List<DistributionMember> listLeft;
	private List<DistributionMember> listRight;
	private Map<String, Integer> memberScheduleCount;

	public String initList()
	{
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(calendar.getTime());
		return "initlist";
	}

	public String list()
	{
		if (schedule == null)
		{
			schedule = new Schedule();
		}
		List<Schedule> list = scheduleService.queryList(schedule);
		returnObject = new DataTableReturnObject<Schedule>();
		returnObject.setData(list);
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		returnObject.setRecordsTotal(list == null ? 0 : list.size());
		return "list";
	}

	public String initAdd()
	{
		return "initAdd";
	}

	public String initEdit()
	{
		schedule = scheduleService.findById(schedule.getScheduleId());
		return "initEdit";
	}

	public String add()
	{
		message = new Message();
		message.setIsSuccess(true);
		try
		{
			if (schedule.getScheduleId() == null)
			{
				List<Schedule> list = scheduleService.queryList(schedule);
				if (list.size() >= 3)
				{
					message.setIsSuccess(false);
					return "add";
				}
				schedule.setScheduleName(schedule.getScheduleStart() + "-"
						+ schedule.getScheduleEnd());
				scheduleService.saveSchedule(schedule);
			} else
			{
				schedule.setScheduleName(schedule.getScheduleStart() + "-"
						+ schedule.getScheduleEnd());
				scheduleService.updateSchedule(schedule);
			}
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			if (schedule.getScheduleId() == null)
			{
				log.error("添加班次失败" + e.getMessage());
			} else
			{
				log.error("修改班次失败" + e.getMessage());
			}
		}
		return "add";
	}

	public String initDelete()
	{
		Long id = schedule.getScheduleId();
		schedule = scheduleService.findById(id);
		return "initDelete";
	}

	public String delete()
	{
		message = new Message();
		message.setIsSuccess(true);
		try
		{
			schedule.setIsDeleted(1);
			scheduleService.updateSchedule(schedule);
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			log.error("删除班次失败" + e.getMessage());
		}
		return "delete";
	}

	public String memberScheduleCount()
	{
		if (shift == null)
		{
			shift = new ShiftSchedule();
		}
		Integer countMemberCount = shiftScheduleService.countMemberCount(shift);
		Integer countMemberSchuduleCount = shiftScheduleService
				.countMemberSchuduleCount(shift);
		memberScheduleCount = new HashMap<String, Integer>();
		memberScheduleCount.put("countMemberCount", countMemberCount);
		memberScheduleCount.put("countMemberSchuduleCount", countMemberSchuduleCount);
		return "memberScheduleCount";
	}

	public String shiftList()
	{
		if (shift == null)
		{
			shift = new ShiftSchedule();
		}
		List<ShiftSchedule> listShift = shiftScheduleService.queryList(shift);
		schedule = new Schedule();
		schedule.setDistributionStationId(shift.getDistributionStationId());
		List<Schedule> listSchedule = scheduleService.queryList(schedule);

		List<ShiftSchedule> list = new ArrayList<ShiftSchedule>();

		if (listSchedule != null)
		{
			for (Schedule sc : listSchedule)
			{
				ShiftSchedule ssc = new ShiftSchedule();
				ssc.setScheduleName(sc.getScheduleName());
				ssc.setScheduleDate(shift.getScheduleDate());
				ssc.setScheduleId(sc.getScheduleId());
				ssc.setDistributionStationId(sc.getDistributionStationId());
				if (listShift != null)
				{
					for (ShiftSchedule shi : listShift)
					{
						if (shi.getScheduleName().equals(ssc.getScheduleName()))
						{
							ssc.setDistributionMemberNames(shi
									.getDistributionMemberNames());
							ssc.setShiftScheduleId(shi.getShiftScheduleId());
							break;
						}
					}
				}

				list.add(ssc);
			}
		}
		returnObject = new DataTableReturnObject<ShiftSchedule>();
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(calendar.getTime());
		if (shift.getScheduleDate().compareTo(today) > 0)
		{
			returnObject.setData(list);
		} else
		{
			returnObject.setData(listShift);
		}
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		return "shiftlist";
	}

	public String queryEvent() throws ParseException
	{
		eventList = new ArrayList<EventSource>();
		List<String> dateList = new ArrayList<String>();
		List<String> currentDateList = DateUtils.dateToWeek(new Date());
		String nextWeek = DateUtils.returnDate(7);
		List<String> nextDateList = DateUtils.dateToWeek(new SimpleDateFormat(
				"yyyy-MM-dd").parse(nextWeek));
		dateList.addAll(currentDateList);
		dateList.addAll(nextDateList);
		for (String date : dateList)
		{
			shift.setScheduleDate(date);
			Integer count = shiftScheduleService.queryListCount(shift);
			if (count != null && count > 0)
			{
				EventSource event = new EventSource();
				event.setTitle("已排班");
				event.setStart(date);
				event.setBackgroundColor("green");
				eventList.add(event);
			} else
			{
				EventSource event = new EventSource();
				event.setTitle("待排班");
				event.setStart(date);
				event.setBackgroundColor("red");
				eventList.add(event);
			}
		}
		return "eventSource";
	}

	public String initAddShiftNew()
	{
		Schedule schudule = new Schedule();
		schudule.setDistributionStationId(shift.getDistributionStationId());
		schudule.setScheduleId(shift.getScheduleId());
		schudule.setScheduleDate(shift.getScheduleDate());
		schudule.setScheduleStart(shift.getScheduleName().split("-")[0]);
		schudule.setScheduleEnd(shift.getScheduleName().split("-")[1]);
		List<DistributionMember> listAll = distributionMemberService
				.selectAllMember(schudule);
		List<DistributionMember> listRepeat = distributionMemberService
				.selectRepeatMember(schudule);
		listLeft = new ArrayList<DistributionMember>();
		listRight = new ArrayList<DistributionMember>();
		if (shift.getShiftScheduleId() == null)
		{
			// 添加 distributionStationId scheduleId scheduleDate
			// 查询出所有该站点的小哥 - 查询出和这个时间重复的小哥----可参与排班的 （左侧listLeft）
			if (listAll != null)
			{
				for (DistributionMember m1 : listAll)
				{
					boolean hasRepeat = false;
					if (listRepeat != null)
					{
						for (DistributionMember m2 : listRepeat)
						{
							if (m1.getDistributionMemberId().equals(
									m2.getDistributionMemberId()))
							{
								hasRepeat = true;
								break;
							}
						}
					}
					if (!hasRepeat)
					{
						listLeft.add(m1);
					}
				}
			}
			return "initAddShiftNew";

		} else
		{
			// 更改 distributionStationId scheduleId scheduleDate shiftScheduleId
			// 查询出所有该站点的小哥 - 查询出和这个时间重复的小哥 - 已经排班的小哥信息 ----可参与排班的（左侧）
			// 已经排班的小哥信息 （右侧listRight）
			List<DistributionMember> listSchedule = distributionMemberService
					.selectScheduleMember(schudule);

			for (DistributionMember m1 : listAll)
			{
				boolean hasRepeat = false;
				if (listRepeat != null)
				{
					for (DistributionMember m2 : listRepeat)
					{
						if (m1.getDistributionMemberId().equals(
								m2.getDistributionMemberId()))
						{
							hasRepeat = true;
							break;
						}
					}
				}

				if (listSchedule != null)
				{
					for (DistributionMember m3 : listSchedule)
					{
						if (m1.getDistributionMemberId().equals(
								m3.getDistributionMemberId()))
						{
							hasRepeat = true;
							listRight.add(m1);
							break;
						}
					}
				}

				if (!hasRepeat)
				{
					listLeft.add(m1);
				}

			}
			return "initAddShiftNew";
		}

	}

	public String initAddShift()
	{
		String date = getRequest().getParameter("date");
		shift = new ShiftSchedule();
		shift.setScheduleDate(date);
		Long distributionStationId = UserContext.getUser()
				.getStationId();
		station = distributionStationService.findById(distributionStationId);
		// 班次列表
		Schedule schedule = new Schedule();
		schedule.setDistributionStationId(distributionStationId);
		scheduleList = scheduleService.queryList(schedule);
		// 配送员列表
		memberList = new ArrayList<DistributionMember>();
		DistributionMember member = new DistributionMember();
		member.setDistributionStationId(distributionStationId);
		List<DistributionMember> list = distributionMemberService
				.queryList(member);
		for (DistributionMember disM : list)
		{
			MemberSchedule mem = new MemberSchedule();
			mem.setDistributionStationId(distributionStationId);
			mem.setDistributionMemberId(disM.getDistributionMemberId());
			mem.setScheduleDate(date);
			String haveShift = memberScheduleService.queryHaveShifts(mem);
			if (null != haveShift)
			{
				disM.setHaveShifts(haveShift);
			}
			memberList.add(disM);
		}
		return "initAddShift";
	}

	public String addShift()
	{
		message = new Message();
		message.setIsSuccess(true);
		List<MemberSchedule> mScheduleList = new ArrayList<MemberSchedule>();
		List<Long> ids = shift.getDistributionMemberIds();
		if (ids != null)
		{
			for (Long id : ids)
			{
				MemberSchedule ms = new MemberSchedule();
				ms.setDistributionMemberId(id);
				ms.setScheduleId(shift.getScheduleId());
				ms.setDistributionStationId(shift.getDistributionStationId());
				mScheduleList.add(ms);
			}
		}

		try
		{
			scheduleService.saveMemberAndShift(mScheduleList, shift);
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			e.printStackTrace();
			log.error("添加排班失败" + e.getCause());
		}
		return "addShift";
	}

	public String initDeleteShift()
	{
		Long id = shift.getShiftScheduleId();
		shift = shiftScheduleService.findById(id);
		return "initDeleteShift";
	}

	public String deleteShift()
	{
		message = new Message();
		message.setIsSuccess(true);
		// 删除记录表和关系表
		try
		{
			scheduleService.deleteMemberAndShift(shift.getShiftScheduleId());
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			log.error("删除排班失败" + e.getMessage());
		}
		return "deleteShift";
	}

	public String dateSchedule()
	{
		scheduleViewList = new ArrayList<DateScheduleView>();
		for (int i = 0; i < 7; i++)
		{
			Calendar calendar = new GregorianCalendar();
			calendar.add(calendar.DATE, i);
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
			DateScheduleView view = new DateScheduleView();
			view.setScheduleDate(myFmt.format(calendar.getTime()));
			DateSchedule schedule1 = new DateSchedule();
			schedule1.setScheduleDate(view.getScheduleDate());
			schedule1.setScheduleId(schedule.getDistributionStationId());

			view.setScheduleDateStatu(shiftScheduleService
					.queryDateCount(schedule1));
			scheduleViewList.add(view);
		}
		returnObject = new DataTableReturnObject<ShiftSchedule>();
		returnObject.setData(scheduleViewList);
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		return "dateSchedule";
	}

	public IScheduleService getScheduleService()
	{
		return scheduleService;
	}

	public void setScheduleService(IScheduleService scheduleService)
	{
		this.scheduleService = scheduleService;
	}

	public List<Schedule> getScheduleList()
	{
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList)
	{
		this.scheduleList = scheduleList;
	}

	public Schedule getSchedule()
	{
		return schedule;
	}

	public void setSchedule(Schedule schedule)
	{
		this.schedule = schedule;
	}

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

	public DataTableReturnObject getReturnObject()
	{
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject returnObject)
	{
		this.returnObject = returnObject;
	}

	public IDistributionStationService getDistributionStationService()
	{
		return distributionStationService;
	}

	public void setDistributionStationService(
			IDistributionStationService distributionStationService)
	{
		this.distributionStationService = distributionStationService;
	}

	public ShiftSchedule getShift()
	{
		return shift;
	}

	public void setShift(ShiftSchedule shift)
	{
		this.shift = shift;
	}

	public DistributionStation getStation()
	{
		return station;
	}

	public void setStation(DistributionStation station)
	{
		this.station = station;
	}

	public List<EventSource> getEventList()
	{
		return eventList;
	}

	public void setEventList(List<EventSource> eventList)
	{
		this.eventList = eventList;
	}

	public IDistributionMemberService getDistributionMemberService()
	{
		return distributionMemberService;
	}

	public void setDistributionMemberService(
			IDistributionMemberService distributionMemberService)
	{
		this.distributionMemberService = distributionMemberService;
	}

	public List<DistributionMember> getMemberList()
	{
		return memberList;
	}

	public void setMemberList(List<DistributionMember> memberList)
	{
		this.memberList = memberList;
	}

	public List<ShiftSchedule> getShiftList()
	{
		return shiftList;
	}

	public void setShiftList(List<ShiftSchedule> shiftList)
	{
		this.shiftList = shiftList;
	}

	public IMemberScheduleService getMemberScheduleService()
	{
		return memberScheduleService;
	}

	public void setMemberScheduleService(
			IMemberScheduleService memberScheduleService)
	{
		this.memberScheduleService = memberScheduleService;
	}

	public IShiftScheduleService getShiftScheduleService()
	{
		return shiftScheduleService;
	}

	public void setShiftScheduleService(
			IShiftScheduleService shiftScheduleService)
	{
		this.shiftScheduleService = shiftScheduleService;
	}

	public List<DateScheduleView> getScheduleViewList()
	{
		return scheduleViewList;
	}

	public void setScheduleViewList(List<DateScheduleView> scheduleViewList)
	{
		this.scheduleViewList = scheduleViewList;
	}

	public String getToday()
	{
		return today;
	}

	public void setToday(String today)
	{
		this.today = today;
	}

	public List<DistributionMember> getListLeft()
	{
		return listLeft;
	}

	public void setListLeft(List<DistributionMember> listLeft)
	{
		this.listLeft = listLeft;
	}

	public List<DistributionMember> getListRight()
	{
		return listRight;
	}

	public void setListRight(List<DistributionMember> listRight)
	{
		this.listRight = listRight;
	}

	public Map<String, Integer> getMemberScheduleCount()
	{
		return memberScheduleCount;
	}

	public void setMemberScheduleCount(Map<String, Integer> memberScheduleCount)
	{
		this.memberScheduleCount = memberScheduleCount;
	}

}
