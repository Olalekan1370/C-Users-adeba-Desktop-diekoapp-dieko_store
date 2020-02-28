/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.python.icu.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import timsoft.ehr.org.model.VwTopBalance;
import timsoft.ehr.org.model.VwTranReports;
import timsoft.ehr.org.repository.AppService;
import timsoft.ehr.org.utils.AppHelper;
import timsoft.ehr.org.utils.FacesUtils;

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
    private BarChartModel barModel;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        datalist = new ArrayList<>();
        barModel = new BarChartModel();
        donutModel = new DonutChartModel();
        reload();
    }

    public void initChart() {
        plotBarModel();
        plotDonutModel();
    }

    String getMonth(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLLL", Locale.getDefault());
        return dateFormat.format(d);
    }

    public void plotBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        datalist.stream().map((rs) -> {
            values.add(rs.getQuantity());
            return rs;
        }).forEachOrdered((rs) -> {
            labels.add(getMonth(rs.getTrandate()));
        });

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Transaction Report");
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < datalist.size(); i++) {
            bgColor.add("rgba(" + r.nextInt(255) + ", " + r.nextInt(99) + ", " + r.nextInt(132) + ", " + r.nextDouble() + ")");
        }
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();

        for (int i = 0; i < datalist.size(); i++) {

            borderColor.add("rgb(" + r.nextInt(255) + ", " + r.nextInt(99) + ", " + r.nextInt(132) + ")");

        }
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Transaction Report");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);

    }

    public void plotDonutModel() {
        donutModel = new DonutChartModel();

        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        datalist.stream().map((rs) -> {
            values.add(rs.getProfit()+rs.getGain());
            return rs;
        }).forEachOrdered((rs) -> {
            labels.add(getMonth(rs.getTrandate()));
           // labels.add(getMonth(rs.getTrandate())+" - "+rs.getProductname());
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
    }

        public void reload() {
        Calendar cl = Calendar.getInstance();
        datalist = service.getVwTranReportsRepo().listByDateRange(cl.get(Calendar.YEAR));
        List<VwTopBalance> data = service.getVwTopBalanceRepo().findAll();
        if(data.isEmpty()==false){
            top =data.get(0);
        }
        if(datalist.isEmpty()==false){
            initChart();
        }
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
        if(datalist.isEmpty()==false){
            initChart();
        }
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

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

}
