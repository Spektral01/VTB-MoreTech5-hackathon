package com.example.test.ui
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.itsc.tuwoda.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.ScreenPoint
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.LinearRing
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polygon
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.sqrt

class MapViewModel(
    var mapView: MapView,
    var context: Context,
): ViewModel() {

    var bankPlacemarks:MutableList<PlacemarkMapObject> = mutableListOf()
    var bankId:MutableList<Int> = mutableListOf()
    var bankPlacemarkListeners:MutableList<MapObjectTapListener> = mutableListOf()
    var bankMap: MutableMap<Int, PlacemarkMapObject> = mutableMapOf()
    var roadPolyline:MutableList<Polyline?> = mutableListOf()
    var roadS:MutableList<Double?> = mutableListOf()


    val MoscowPolygon = listOf(
        Point(55.749702, 37.315382),
        Point(55.842162, 37.353248),
        Point(55.842162, 37.353248),
        Point(55.926941, 37.535510),
        Point(55.902233, 37.711488),
        Point(55.840647, 37.847960),
        Point(55.772383, 37.861428),
        Point(55.655175, 37.860684),
        Point(55.579408, 37.727638),
        Point(55.570834, 37.562180),
        Point(55.570834, 37.562180),
        Point(55.706292, 37.322986)
    )

    fun addMoskowPolygon(){
        val polygon = Polygon(LinearRing(MoscowPolygon), emptyList())
        val polygonMapObject = mapView.map.mapObjects.addPolygon(polygon)

        polygonMapObject.apply {
            strokeWidth = 1.5f
            strokeColor = ContextCompat.getColor(context, R.color.purple_200)
        }
    }

    private var imageProviders: kotlin.collections.Map<String, ImageProvider> = mapOf(
        "dollar" to ImageProvider.fromResource(context,R.drawable.ic_pin),
        "start_end" to ImageProvider.fromResource(context,R.drawable.ic_start_end),
    )
    var myGeolocationPlacemark: PlacemarkMapObject? = null


    fun setMyLocation(point: Point){
        myGeolocationPlacemark = mapView.map.mapObjects.addPlacemark(
            point,
            imageProviders["start_end"]!!
        )
    }
    fun goToMyLocation(){
        mapView.map.apply {
            myGeolocationPlacemark?.let {
                move(
                    CameraPosition(
                        it.geometry,
                        cameraPosition.zoom,
                        cameraPosition.azimuth,
                        cameraPosition.tilt),
                    Animation(Animation.Type.SMOOTH, 0f),
                    null
                )
            }
        }
    }
    fun addRoute(){
        val l:MutableList<Point> = mutableListOf()
        bankPlacemarks.forEach {
            l.add(it.geometry)
        }
        DriwerRoad(l)

    }

    fun pushPath(myGeo:Pair<Double, Double>,tar:Pair<Double, Double>){
        val drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()

        val drivingOptions = DrivingOptions().apply {
            routesCount = 1
        }
        val vehicleOptions = VehicleOptions()
        val points = buildList {
            add(RequestPoint(Point(myGeo.first,myGeo.second), RequestPointType.WAYPOINT, null))
            add(RequestPoint(Point(tar.first, tar.second), RequestPointType.WAYPOINT, null))
        }


        val drivingRouteListener = object : DrivingSession.DrivingRouteListener {
            override fun onDrivingRoutes(drivingRoutes: MutableList<DrivingRoute>) {
                var s:Double? = null

                var sum:Double = 0.0;
                val p = drivingRoutes[0].geometry.points
                for(i in 1 until p.size){
                    sum += sqrt(
                        pow(abs(p[i-1].longitude - p[i].longitude),2.0)
                                +
                                pow(abs(p[i-1].latitude - p[i].latitude),2.0)
                    )
                }
                s = sum

                roadS.add(s)
                roadPolyline.add(drivingRoutes[0].geometry)
            }

            override fun onDrivingRoutesError(p0: Error) {
                // Handle request routes error ...
            }
        }

        val drivingSession = drivingRouter.requestRoutes(
            points,
            drivingOptions,
            vehicleOptions,
            drivingRouteListener
        )
    }

    private fun addRoadToBank(polyline: Polyline){
        roadPolyline.add(polyline)
    }

    private fun DriwerRoad(l:List<Point>){
        mapView.map.mapObjects.addPolyline(Polyline(l))
    }

    fun initBankPlacemarks(id:List<Int>, l:List<Pair<Double, Double>>, onTap:(Int)->Unit = {}){
        for(i in l.indices){
            val imageProvider = ImageProvider.fromResource(context,R.drawable.ic_pin)
            val pm = mapView.map.mapObjects.addPlacemark(
                Point(l[i].first,l[i].second),
                imageProvider
            )
            bankMap[id[i]] = pm

            val tapListener = object : MapObjectTapListener{
                override fun onMapObjectTap(p0: MapObject, p1: Point): Boolean {
                    val keys = bankMap.filterValues { it == p0 }.keys
                    val idb = keys.toList()[0]
                    onTap(idb)
                    return true
                }
            }
            pm.addTapListener(tapListener)

            bankPlacemarkListeners.add(tapListener)
            bankPlacemarks.add(pm)
            bankId.add(id[i])

        }
    }

    fun initRoads(myGeo: Point){
        bankPlacemarks.forEach { pm->
            pushPath(
                convertPointToPair(myGeo),
                convertPointToPair(pm.geometry)
            )
        }
    }

    fun getRoadsInfo():RoadsInfo{
        return RoadsInfo(
            s = roadS,
            polylines = roadPolyline,
            ids = bankId
        )
    }

    fun getCenterPoint():Pair<Double,Double>{
        val centerX = mapView.width / 2f
        val centerY = mapView.height / 2f
        val centerPoint = ScreenPoint(centerX, centerY)
        val worldPoint = mapView.screenToWorld(centerPoint)
        return convertPointToPair(worldPoint)
    }

    private fun convertPairToPoint(pair:Pair<Double,Double>):Point{
        return Point(pair.first, pair.second)
    }
    private fun convertPointToPair(point:Point):Pair<Double,Double>{
        return Pair(point.latitude, point.longitude)
    }
}

data class RoadsInfo(
    var s:List<Double?>,
    var polylines:List<Polyline?>,
    var ids:List<Int>,
)


/*
private fun updateCenterPlacemar(imageProvider:ImageProvider){
        val centerX = mapView.width / 2f
        val centerY = mapView.height / 2f
        val centerPoint = ScreenPoint(centerX, centerY)
        val worldPoint = mapView.screenToWorld(centerPoint)
        tempPlacemark = mapView.map.mapObjects.addPlacemark(
            worldPoint, imageProvider
        )
    }
    fun goTOAddNewPlacemarkState(){
        addNewPlacemarkState = true
        val imageProvider = ImageProvider.fromResource(context,R.drawable.ic_pin)
        updateCenterPlacemar(imageProvider)

        cameraListener = object:CameraListener{
            override fun onCameraPositionChanged(
                p0: Map,
                p1: CameraPosition,
                p2: CameraUpdateReason,
                p3: Boolean
            ) {
                tempPlacemark?.let {
                    mapView.map.mapObjects.remove(it)
                }

                updateCenterPlacemar(imageProvider)
            }
        }
        cameraListener?.let {
            mapView.map.addCameraListener(it)
        }
    }

    fun goToBasicState(){
        addNewPlacemarkState = false
        tempPlacemark?.let {
            it.setText("1231232")
            bankPlacemarks.add(it)
        }
        cameraListener?.let {
            mapView.map.removeCameraListener(it)
        }
        tempPlacemark = null
    }
 */

