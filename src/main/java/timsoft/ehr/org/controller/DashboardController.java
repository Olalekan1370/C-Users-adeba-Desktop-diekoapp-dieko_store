/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.dto.Chartsdata;
import timsoft.ehr.org.model.VwTopBalance;
import timsoft.ehr.org.model.VwTranReports;
import timsoft.ehr.org.repository.AppService;
import timsoft.ehr.org.utils.AppHelper;
import timsoft.ehr.org.utils.FacesUtils;
import timsoft.ehr.org.utils.Reportutils;

/**
 *
 * @author JIDEX
 */
@Component
@Scope("session")
public class DashboardController implements Serializable {

    private List<VwTranReports> datalist;
    @Autowired
    AppService service;
    private VwTopBalance top;
    private DonutChartModel donutModel;
    private BarChartModel salesBar;
    private HashMap<String, Chartsdata> maps;

    @PostConstruct
    public void init() {
        datalist = new ArrayList<>();
        maps = new HashMap<>();
        donutModel = new DonutChartModel();
        reload();
    }

    public void initChart() {
        createBarModel();
        plotDonutModel();
    }

    String getMonth(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLLL", Locale.getDefault());
        return dateFormat.format(d);
    }
    public void loadDataIntoMap(){
        datalist.forEach((rs) -> {
            updateMap(rs.getProductname(), rs.getQuantity());
        });
    }
public void updateMap(String key, Double quantity){
    if(maps.containsKey(key)){
        Chartsdata rs = maps.get(key);
        rs.setQuantity(rs.getQuantity()+quantity);
        maps.put(key, rs);
    }else{
        Chartsdata rs = new Chartsdata();
        rs.setProductname(key);
         rs.setQuantity(quantity);
        maps.put(key, rs);
    }
}
    public void initializeModel(ChartSeries profits, ChartSeries cost, ChartSeries quantity,
            ChartSeries loss, ChartSeries gain) {
        cost.set(Reportutils.getMonthName(1), 0.0);
        cost.set(Reportutils.getMonthName(2), 0.0);
        cost.set(Reportutils.getMonthName(3), 0.0);
        cost.set(Reportutils.getMonthName(4), 0.0);
        cost.set(Reportutils.getMonthName(5), 0.0);
        cost.set(Reportutils.getMonthName(6), 0.0);
        cost.set(Reportutils.getMonthName(7), 0.0);
        cost.set(Reportutils.getMonthName(8), 0.0);
        cost.set(Reportutils.getMonthName(9), 0.0);
        cost.set(Reportutils.getMonthName(10), 0.0);
        cost.set(Reportutils.getMonthName(11), 0.0);
        cost.set(Reportutils.getMonthName(12), 0.0);

        profits.set(Reportutils.getMonthName(1), 0.0);
        profits.set(Reportutils.getMonthName(2), 0.0);
        profits.set(Reportutils.getMonthName(3), 0.0);
        profits.set(Reportutils.getMonthName(4), 0.0);
        profits.set(Reportutils.getMonthName(5), 0.0);
        profits.set(Reportutils.getMonthName(6), 0.0);
        profits.set(Reportutils.getMonthName(7), 0.0);
        profits.set(Reportutils.getMonthName(8), 0.0);
        profits.set(Reportutils.getMonthName(9), 0.0);
        profits.set(Reportutils.getMonthName(10), 0.0);
        profits.set(Reportutils.getMonthName(11), 0.0);
        profits.set(Reportutils.getMonthName(12), 0.0);

        loss.set(Reportutils.getMonthName(1), 0.0);
        loss.set(Reportutils.getMonthName(2), 0.0);
        loss.set(Reportutils.getMonthName(3), 0.0);
        loss.set(Reportutils.getMonthName(4), 0.0);
        loss.set(Reportutils.getMonthName(5), 0.0);
        loss.set(Reportutils.getMonthName(6), 0.0);
        loss.set(Reportutils.getMonthName(7), 0.0);
        loss.set(Reportutils.getMonthName(8), 0.0);
        loss.set(Reportutils.getMonthName(9), 0.0);
        loss.set(Reportutils.getMonthName(10), 0.0);
        loss.set(Reportutils.getMonthName(11), 0.0);
        loss.set(Reportutils.getMonthName(12), 0.0);

        gain.set(Reportutils.getMonthName(1), 0.0);
        gain.set(Reportutils.getMonthName(2), 0.0);
        gain.set(Reportutils.getMonthName(3), 0.0);
        gain.set(Reportutils.getMonthName(4), 0.0);
        gain.set(Reportutils.getMonthName(5), 0.0);
        gain.set(Reportutils.getMonthName(6), 0.0);
        gain.set(Reportutils.getMonthName(7), 0.0);
        gain.set(Reportutils.getMonthName(8), 0.0);
        gain.set(Reportutils.getMonthName(9), 0.0);
        gain.set(Reportutils.getMonthName(10), 0.0);
        gain.set(Reportutils.getMonthName(11), 0.0);
        gain.set(Reportutils.getMonthName(12), 0.0);
        quantity.set(Reportutils.getMonthName(1), 0.0);
        quantity.set(Reportutils.getMonthName(2), 0.0);
        quantity.set(Reportutils.getMonthName(3), 0.0);
        quantity.set(Reportutils.getMonthName(4), 0.0);
        quantity.set(Reportutils.getMonthName(5), 0.0);
        quantity.set(Reportutils.getMonthName(6), 0.0);
        quantity.set(Reportutils.getMonthName(7), 0.0);
        quantity.set(Reportutils.getMonthName(8), 0.0);
        quantity.set(Reportutils.getMonthName(9), 0.0);
        quantity.set(Reportutils.getMonthName(10), 0.0);
        quantity.set(Reportutils.getMonthName(11), 0.0);
        quantity.set(Reportutils.getMonthName(12), 0.0);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries profits = new ChartSeries();
        ChartSeries costs = new ChartSeries();
        ChartSeries loss = new ChartSeries();
        ChartSeries gain = new ChartSeries();
        ChartSeries quantity = new ChartSeries();
        profits.setLabel("Profits");
        costs.setLabel("Sales");
        quantity.setLabel("Quantity");
        loss.setLabel("Loss");
        gain.setLabel("Gain");
        initializeModel(profits, costs, quantity, loss, gain);

        datalist.stream().map((rs) -> {
            costs.set(rs.getMonths().substring(0, 3), rs.getAmount());
            return rs;
        }).map((rs) -> {
            profits.set(rs.getMonths().substring(0, 3), rs.getProfit());
            return rs;
        }).map((rs) -> {
            loss.set(rs.getMonths().substring(0, 3), rs.getLoss());
            return rs;
        }).map((rs) -> {
            gain.set(rs.getMonths().substring(0, 3), rs.getGain());
            return rs;
        }).forEachOrdered((rs) -> {
            quantity.set(rs.getMonths().substring(0, 3), rs.getQuantity());
           });

        model.addSeries(quantity);
        model.addSeries(costs);
        model.addSeries(loss);
        model.addSeries(profits);
        model.addSeries(gain);

        return model;
    }

    private void createBarModel() {
        salesBar = initBarModel();
        salesBar.setTitle("Sales Report Jan-Dec");
        salesBar.setLegendPosition("s");
salesBar.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
salesBar.setLegendRows(1);
        Axis xAxis = salesBar.getAxis(AxisType.X);
        xAxis.setLabel("Report Months");

        Axis yAxis = salesBar.getAxis(AxisType.Y);
        yAxis.setLabel("Transaction Range");

        if (datalist.isEmpty()) {
            yAxis.setMin(1000);
            yAxis.setMax(10000);
        } else {
            Optional<VwTranReports> min = datalist.stream().min(Comparator.comparingDouble(VwTranReports::getAmount));
            Optional<VwTranReports> max = datalist.stream().max(Comparator.comparingDouble(VwTranReports::getAmount));
            yAxis.setMin(min.get().getAmount());
            yAxis.setMax(max.get().getAmount());
        }
       // salesBar.setStacked(true);
    
        salesBar.setExtender("skinBar");
    }

    public void plotDonutModel() {
        donutModel = new DonutChartModel();

        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        maps.values().stream().map((rs) -> {
            values.add(rs.getQuantity());
            return rs;
        }).forEachOrdered((rs) -> {
            labels.add(rs.getProductname());
        });
       

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < datalist.size(); i++) {

            bgColors.add("rgb(" + r.nextInt(255) + ", " + r.nextInt(99) + ", " + r.nextInt(132) + ")");

        }
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);

        data.setLabels(labels);

        donutModel.setData(data);
        donutModel.setExtender("skinDonut");
    }

    public void reload() {
        Calendar cl = Calendar.getInstance();
        datalist = service.getVwTranReportsRepo().listByDateRange(cl.get(Calendar.YEAR));
        List<VwTopBalance> data = service.getVwTopBalanceRepo().findAll();
        if (data.isEmpty() == false) {
            top = data.get(0);
        }
       
             loadDataIntoMap();
            initChart();
        
    }

    public void filter() {
        AppHelper helper = (AppHelper) FacesUtils.getManagedBean("appHelper");
        switch (helper.getSearchterm()) {
            case "YEAR":
                datalist = service.getVwTranReportsRepo().listByDateRange(helper.getReportyear());
                break;
            case "PRODUCT":
                datalist = service.getVwTranReportsRepo().listByDateRange(helper.getDateFrom(), helper.getDateTo(),
                        helper.getProductname());
                break;
            default:
                datalist = service.getVwTranReportsRepo().listByDateRange(helper.getDateFrom(), helper.getDateTo());
                break;
        }
             loadDataIntoMap();
            initChart();
        
    }

    public List<VwTranReports> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<VwTranReports> datalist) {
        this.datalist = datalist;
    }

    public AppService getService() {
        return service;
    }

    public void setService(AppService service) {
        this.service = service;
    }

    public VwTopBalance getTop() {
        return top;
    }

    public void setTop(VwTopBalance top) {
        this.top = top;
    }

    public BarChartModel getSalesBar() {
        return salesBar;
    }

    public void setSalesBar(BarChartModel salesBar) {
        this.salesBar = salesBar;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

    public HashMap<String, Chartsdata> getMaps() {
        return maps;
    }

    public void setMaps(HashMap<String, Chartsdata> maps) {
        this.maps = maps;
    }

}
