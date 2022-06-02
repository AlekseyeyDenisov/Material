package ru.dw.material.dto

import com.google.gson.annotations.SerializedName

data class ResponseEarth(

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("identifier")
    val identifier: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("lunar_j2000_position")
    val lunarJ2000Position: LunarJ2000Position,

    @field:SerializedName("attitude_quaternions")
    val attitudeQuaternions: AttitudeQuaternions,

    @field:SerializedName("dscovr_j2000_position")
    val dscovrJ2000Position: DscovrJ2000Position,

    @field:SerializedName("caption")
    val caption: String,

    @field:SerializedName("sun_j2000_position")
    val sunJ2000Position: SunJ2000Position,

    @field:SerializedName("version")
    val version: String,

    @field:SerializedName("coords")
    val coords: Coords,

    @field:SerializedName("centroid_coordinates")
    val centroidCoordinates: CentroidCoordinates
)

data class DscovrJ2000Position(

    @field:SerializedName("x")
    val X: Double,

    @field:SerializedName("y")
    val Y: Double,

    @field:SerializedName("z")
    val Z: Double
)

data class SunJ2000Position(

    @field:SerializedName("x")
    val X: Double,

    @field:SerializedName("y")
    val Y: Double,

    @field:SerializedName("z")
    val Z: Double
)

data class Coords(

    @field:SerializedName("lunar_j2000_position")
    val lunarJ2000Position: LunarJ2000Position,

    @field:SerializedName("attitude_quaternions")
    val attitudeQuaternions: AttitudeQuaternions,

    @field:SerializedName("dscovr_j2000_position")
    val dscovrJ2000Position: DscovrJ2000Position,

    @field:SerializedName("sun_j2000_position")
    val sunJ2000Position: SunJ2000Position,

    @field:SerializedName("centroid_coordinates")
    val centroidCoordinates: CentroidCoordinates
)

data class LunarJ2000Position(

    @field:SerializedName("x")
    val X: Double,

    @field:SerializedName("y")
    val Y: Double,

    @field:SerializedName("z")
    val Z: Double
)

data class CentroidCoordinates(

    @field:SerializedName("lon")
    val lon: Double,

    @field:SerializedName("lat")
    val lat: Double
)

data class AttitudeQuaternions(

    @field:SerializedName("q1")
    val q1: Double,

    @field:SerializedName("q2")
    val q2: Double,

    @field:SerializedName("q3")
    val q3: Double,

    @field:SerializedName("q0")
    val q0: Double
)
