package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.common.DataTableReturnObject;

public class DistributionStationAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DistributionStationAction.class);
	private DataTableReturnObject<DistributionStation> returnObject;
	private Message message;
	private IDistributionStationService distributionStationService;
	private IDistributionMemberService distributionMemberService;
	private ICityService cityService;
	private DistributionStation station;
	private List<City> cityList;
	private List<DistributionStation> stationList;
	private String stationStatus;
	private Long stationId;
	private Integer stationStatu;

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

	public String initList()
	{
		cityList = cityService.queryList();
		return "initlist";
	}

	public String list()
	{
		if (null == station)
		{
			station = new DistributionStation();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		station.setFirstRow(Integer.parseInt(start));
		station.setPageRows(Integer.parseInt(length));

		if (!stationStatus.equals("1,2"))
		{
			List<Integer> l = new ArrayList<Integer>();
			l.add(Integer.parseInt(stationStatus.split(",")[0]));
			l.add(Integer.parseInt(stationStatus.split(",")[1]));
			station.setStatusList(l);
		}

		Integer count = distributionStationService.queryListCount(station);
		List<DistributionStation> list = distributionStationService
				.queryList(station);
		returnObject = new DataTableReturnObject<DistributionStation>();
		returnObject.setData(list);
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		returnObject.setRecordsTotal(count);
		returnObject.setRecordsFiltered(count);
		return "list";
	}

	public String initAdd()
	{
		cityList = cityService.queryList();
		return "initAdd";
	}

	public String add()
	{
		message = new Message();
		message.setIsSuccess(true);
		try
		{
			distributionStationService.saveStation(station);
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			log.error("添加物流点失败" + e.getMessage());
		}
		return "add";
	}

	public String initEdit()
	{
		cityList = cityService.queryList();
		Long id = station.getDistributionStationId();
		station = distributionStationService.findById(id);
		return "initEdit";
	}

	public String updateStatus()
	{
		message = new Message();
		message.setIsSuccess(true);
		station = new DistributionStation();
		station.setStatus(stationStatu);
		station.setDistributionStationId(stationId);
		distributionStationService.updateStation(station);
		return "update";
	}

	public String edit()
	{
		message = new Message();
		message.setIsSuccess(true);
		try
		{
			distributionStationService.updateStation(station);
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			log.error("编辑物流点失败" + e.getMessage());
		}
		return "edit";
	}

	public String initDelete()
	{
		Long id = station.getDistributionStationId();
		station = distributionStationService.findById(id);
		return "initDelete";
	}

	public String delete()
	{
		message = new Message();
		message.setIsSuccess(true);
		message.setInfo("删除成功");
		try
		{
			DistributionMember member = new DistributionMember();
			member.setDistributionStationId(UserContext.getUser()
					.getStationId());
			int count = distributionMemberService.queryListCount(member);
			if (count == 0)
			{
				station.setIsDeleted(1);
				distributionStationService.updateStation(station);
			} else
			{
				message.setIsSuccess(false);
				message.setInfo("删除失败,物流点还有物流人员");
			}
		} catch (Exception e)
		{
			message.setIsSuccess(false);
			message.setInfo("删除失败");
			log.error("删除物流点失败" + e.getMessage());
		}
		return "delete";
	}

	public String stationForCity()
	{
		DistributionStation station = new DistributionStation();
		String cityId = getRequest().getParameter("cityId");
		String name = getRequest().getParameter("q");
		station.setCityId(Long.parseLong(cityId));
		station.setDistributionStationName(name);
		stationList = distributionStationService.queryList(station);
		return "stationForCity";
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

	public DistributionStation getStation()
	{
		return station;
	}

	public void setStation(DistributionStation station)
	{
		this.station = station;
	}

	public DataTableReturnObject<DistributionStation> getReturnObject()
	{
		return returnObject;
	}

	public void setReturnObject(
			DataTableReturnObject<DistributionStation> returnObject)
	{
		this.returnObject = returnObject;
	}

	public ICityService getCityService()
	{
		return cityService;
	}

	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}

	public List<City> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<City> cityList)
	{
		this.cityList = cityList;
	}

	public List<DistributionStation> getStationList()
	{
		return stationList;
	}

	public void setStationList(List<DistributionStation> stationList)
	{
		this.stationList = stationList;
	}

	public String getStationStatus()
	{
		return stationStatus;
	}

	public void setStationStatus(String stationStatus)
	{
		this.stationStatus = stationStatus;
	}

	public Long getStationId()
	{
		return stationId;
	}

	public void setStationId(Long stationId)
	{
		this.stationId = stationId;
	}

	public Integer getStationStatu()
	{
		return stationStatu;
	}

	public void setStationStatu(Integer stationStatu)
	{
		this.stationStatu = stationStatu;
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

}
