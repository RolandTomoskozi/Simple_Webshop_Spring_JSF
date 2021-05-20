package at.rt.simple.webshop.web.view.controller;

import at.rt.simple.webshop.core.model.dto.KundeDto;
import at.rt.simple.webshop.core.service.api.IKundeService;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * BackingBean fuer das Dashboard. Beinhaltet das Dashboard Model und die angezeigten Daten im Dashboard.
 *
 * @author Roland Tömösközi (roland.toemoeskoezi@activesolution.at)
 * Created on 19.05.2021
 */
@Named
@Scope("session")
public class DashboardBean {
    @Autowired
    private IKundeService kundeService;

    private DefaultDashboardModel model;
    private PieChartModel kundeChartModel;

    /**
     * Datenmodel fuer Dashboard (Rows/Columns)
     *
     * @return Model fuer Dashboard
     */
    public DashboardModel getModel() {
        if (model == null) {
            // DashboardModel initialisieren
            model = new DefaultDashboardModel();
            DashboardColumn column1 = new DefaultDashboardColumn();

            column1.addWidget("links");
            column1.addWidget("kunden");

            model.addColumn(column1);
        }

        return model;
    }

    /**
     * Model fuer Kunden-Chart mit Bestellungen pro Kunde
     *
     * @return Model fuer Chart
     */
    public PieChartModel getKundeChartModel() {
        if (kundeChartModel == null) {
            kundeChartModel = new PieChartModel();

            ChartData data = new ChartData();

            PieChartDataSet dataSet = new PieChartDataSet();
            List<Number> values = new ArrayList<>();
            List<String> labels = new ArrayList<>();

            List<String> bgColors = new ArrayList<>();
            bgColors.add("rgba(255, 99, 132, 0.8)");
            bgColors.add("rgba(255, 159, 64, 0.8)");
            bgColors.add("rgba(255, 205, 86, 0.8)");
            bgColors.add("rgba(75, 192, 192, 0.8)");
            bgColors.add("rgba(54, 162, 235, 0.8)");
            bgColors.add("rgba(153, 102, 255, 0.8)");
            bgColors.add("rgba(201, 203, 207, 0.8)");
            bgColors.add(" rgba(255, 0, 111, 0.8)");
            bgColors.add("rgba(42, 0, 111, 0.8)");
            bgColors.add("rgba(255, 165, 97, 0.8)");
            bgColors.add("rgba(255, 55, 0, 0.8)");
            bgColors.add("rgba(42, 68, 219, 0.8)");
            bgColors.add("rgba(0, 170, 219, 0.8)");
            bgColors.add("rgba(0, 170, 97, 0.8)");
            bgColors.add("rgba(0, 170, 97, 0.8)");
            bgColors.add("rgba(134, 170, 97, 0.8)");

            dataSet.setBackgroundColor(bgColors);

            List<KundeDto> kundeInfo = kundeService.listKundeDto();
            for (KundeDto dto : kundeInfo) {
                values.add(dto.getAnzahlVonBestellung());
                labels.add(dto.getKunde().getName());
            }

            // Data
            dataSet.setData(values);
            data.addChartDataSet(dataSet);
            data.setLabels(labels);
            kundeChartModel.setData(data);

            // Options
            Legend legend = new Legend();
            legend.setPosition("left");
            PieChartOptions options = new PieChartOptions();
            options.setLegend(legend);
            kundeChartModel.setOptions(options);
        }
        return kundeChartModel;
    }
}
