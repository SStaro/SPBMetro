import core.Line;
import core.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@DisplayName("Тест методов класса RouteCalculator")
public class RouteCalculatorTests {

    private RouteCalculator routeCalculator;
    private StationIndex stationIndex;
    private List<Station> route;

    @BeforeEach
    public void setUp() {

        stationIndex = new StationIndex();
        routeCalculator = new RouteCalculator(stationIndex);
        route = new ArrayList<>();
        List<Station> connectedStations = new ArrayList<>();


        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        Station firstStationOnLine1 = new Station("Синяя", line1);
        Station secondStationOnLine1 = new Station("Персиковая", line1);

        Station firstStationOnLine2 = new Station("Брючная", line2);
        Station secondStationOnLine2 = new Station("Рукавная", line2);
        Station thirdStationOnLine2 = new Station("Яковая", line2);

        Station firstStationOnLine3 = new Station("Морская", line3);
        Station secondStationOnLine3 = new Station("Деревянная", line3);
        

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(firstStationOnLine1);
        stationIndex.addStation(secondStationOnLine1);
        stationIndex.addStation(firstStationOnLine2);
        stationIndex.addStation(secondStationOnLine2);
        stationIndex.addStation(thirdStationOnLine2);
        stationIndex.addStation(firstStationOnLine3);
        stationIndex.addStation(secondStationOnLine3);

        line1.addStation(firstStationOnLine1);
        line1.addStation(secondStationOnLine1);
        line2.addStation(firstStationOnLine2);
        line2.addStation(secondStationOnLine2);
        line2.addStation(thirdStationOnLine2);
        line3.addStation(firstStationOnLine3);
        line3.addStation(secondStationOnLine3);

        connectedStations.add(firstStationOnLine1);
        connectedStations.add(firstStationOnLine2);

        stationIndex.addConnection(connectedStations);

        connectedStations = new ArrayList<>();

        connectedStations.add(secondStationOnLine2);
        connectedStations.add(firstStationOnLine3);

        stationIndex.addConnection(connectedStations);
    }

    @Test
    @DisplayName("Кратчайший путь: станции на одной линии")
    public void getShortestRouteOnTheLine() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Брючная"), stationIndex.getStation("Яковая"));
        List<Station> expected = new ArrayList<>();

        expected.add(stationIndex.getStation("Брючная"));
        expected.add(stationIndex.getStation("Рукавная"));
        expected.add(stationIndex.getStation("Яковая"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Кратчайший путь: одна пересадка")
    public void getShortestRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Синяя"), stationIndex.getStation("Рукавная"));
        List<Station> expected = new ArrayList<>();

        expected.add(stationIndex.getStation("Синяя"));
        expected.add(stationIndex.getStation("Брючная"));
        expected.add(stationIndex.getStation("Рукавная"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Кратчайший путь: две пересадки")
    public void getShortestRouteWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(stationIndex.getStation("Синяя"), stationIndex.getStation("Деревянная"));
        List<Station> expected = new ArrayList<>();

        expected.add(stationIndex.getStation("Синяя"));
        expected.add(stationIndex.getStation("Брючная"));
        expected.add(stationIndex.getStation("Рукавная"));
        expected.add(stationIndex.getStation("Морская"));
        expected.add(stationIndex.getStation("Деревянная"));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Подсчет времени")
    public void calculateDuration() {
        route = routeCalculator.getShortestRoute(stationIndex.getStation("Синяя"), stationIndex.getStation("Морская"));
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 9.5;
        assertEquals(expected,actual);
    }

}
