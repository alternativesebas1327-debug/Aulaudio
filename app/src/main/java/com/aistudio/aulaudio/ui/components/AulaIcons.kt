package com.aistudio.aulaudio.ui.components

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

object AulaIcons {
    val Mic: ImageVector
        get() = _mic ?: materialIcon(name = "Filled.Mic") {
            materialPath {
                moveTo(12.0f, 14.0f)
                curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f)
                verticalLineTo(5.0f)
                curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f)
                reflectiveCurveTo(9.0f, 3.34f, 9.0f, 5.0f)
                verticalLineToRelative(6.0f)
                curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f)
                close()
                moveTo(17.3f, 11.0f)
                curveToRelative(0.0f, 2.92f, -2.36f, 5.3f, -5.3f, 5.3f)
                reflectiveCurveTo(6.7f, 13.92f, 6.7f, 11.0f)
                horizontalLineTo(5.0f)
                curveToRelative(0.0f, 3.53f, 2.61f, 6.43f, 6.0f, 6.92f)
                verticalLineTo(21.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(-3.08f)
                curveToRelative(3.39f, -0.49f, 6.0f, -3.39f, 6.0f, -6.92f)
                horizontalLineToRelative(-1.7f)
                close()
            }
        }.also { _mic = it }
    private var _mic: ImageVector? = null

    val GraphicEq: ImageVector
        get() = _graphicEq ?: materialIcon(name = "Filled.GraphicEq") {
            materialPath {
                moveTo(7.0f, 18.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(6.0f)
                horizontalLineTo(7.0f)
                verticalLineToRelative(12.0f)
                close()
                moveTo(11.0f, 22.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(2.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(20.0f)
                close()
                moveTo(3.0f, 14.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(-4.0f)
                horizontalLineTo(3.0f)
                verticalLineToRelative(4.0f)
                close()
                moveTo(15.0f, 18.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(6.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(12.0f)
                close()
                moveTo(19.0f, 10.0f)
                verticalLineToRelative(4.0f)
                horizontalLineToRelative(2.0f)
                verticalLineToRelative(-4.0f)
                horizontalLineToRelative(-2.0f)
                close()
            }
        }.also { _graphicEq = it }
    private var _graphicEq: ImageVector? = null

    val Folder: ImageVector
        get() = _folder ?: materialIcon(name = "Filled.Folder") {
            materialPath {
                moveTo(10.0f, 4.0f)
                horizontalLineTo(4.0f)
                curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f)
                lineTo(2.0f, 18.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(16.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(8.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                horizontalLineToRelative(-8.0f)
                lineToRelative(-2.0f, -2.0f)
                close()
            }
        }.also { _folder = it }
    private var _folder: ImageVector? = null

    val Bookmark: ImageVector
        get() = _bookmark ?: materialIcon(name = "Filled.Bookmark") {
            materialPath {
                moveTo(17.0f, 3.0f)
                horizontalLineTo(7.0f)
                curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f)
                lineTo(5.0f, 21.0f)
                lineToRelative(7.0f, -3.0f)
                lineToRelative(7.0f, 3.0f)
                verticalLineTo(5.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                close()
            }
        }.also { _bookmark = it }
    private var _bookmark: ImageVector? = null

    val BookmarkBorder: ImageVector
        get() = _bookmarkBorder ?: materialIcon(name = "Filled.BookmarkBorder") {
            materialPath {
                moveTo(17.0f, 3.0f)
                horizontalLineTo(7.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                verticalLineToRelative(16.0f)
                lineToRelative(7.0f, -3.0f)
                lineToRelative(7.0f, 3.0f)
                verticalLineTo(5.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(17.0f, 18.0f)
                lineToRelative(-5.0f, -2.18f)
                lineTo(7.0f, 18.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(10.0f)
                verticalLineToRelative(13.0f)
                close()
            }
        }.also { _bookmarkBorder = it }
    private var _bookmarkBorder: ImageVector? = null

    val Tune: ImageVector
        get() = _tune ?: materialIcon(name = "Filled.Tune") {
            materialPath {
                moveTo(3.0f, 17.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(6.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineTo(3.0f)
                close()
                moveTo(3.0f, 5.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(10.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(3.0f)
                close()
                moveTo(13.0f, 21.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(8.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(-8.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(6.0f)
                horizontalLineToRelative(2.0f)
                close()
                moveTo(7.0f, 9.0f)
                verticalLineToRelative(2.0f)
                horizontalLineTo(3.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(4.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(9.0f)
                horizontalLineTo(7.0f)
                close()
                moveTo(21.0f, 13.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineTo(11.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(10.0f)
                close()
                moveTo(17.0f, 9.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(7.0f)
                horizontalLineToRelative(2.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineTo(3.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineToRelative(6.0f)
                close()
            }
        }.also { _tune = it }
    private var _tune: ImageVector? = null

    val AccountCircle: ImageVector
        get() = _accountCircle ?: materialIcon(name = "Filled.AccountCircle") {
            materialPath {
                moveTo(12.0f, 2.0f)
                curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f)
                reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f)
                reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f)
                reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f)
                close()
                moveTo(12.0f, 6.0f)
                curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f)
                reflectiveCurveTo(13.93f, 13.0f, 12.0f, 13.0f)
                reflectiveCurveToRelative(-3.5f, -1.57f, -3.5f, -3.5f)
                reflectiveCurveTo(10.07f, 6.0f, 12.0f, 6.0f)
                close()
                moveTo(12.0f, 20.0f)
                curveToRelative(-2.03f, 0.0f, -4.43f, -0.82f, -6.14f, -2.88f)
                curveToRelative(0.04f, -2.03f, 4.09f, -3.12f, 6.14f, -3.12f)
                curveToRelative(2.04f, 0.0f, 6.1f, 1.09f, 6.14f, 3.12f)
                curveTo(16.43f, 19.18f, 14.03f, 20.0f, 12.0f, 20.0f)
                close()
            }
        }.also { _accountCircle = it }
    private var _accountCircle: ImageVector? = null

    val Pause: ImageVector
        get() = _pause ?: materialIcon(name = "Filled.Pause") {
            materialPath {
                moveTo(6.0f, 19.0f)
                horizontalLineToRelative(4.0f)
                verticalLineTo(5.0f)
                horizontalLineTo(6.0f)
                verticalLineToRelative(14.0f)
                close()
                moveTo(14.0f, 5.0f)
                verticalLineToRelative(14.0f)
                horizontalLineToRelative(4.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(-4.0f)
                close()
            }
        }.also { _pause = it }
    private var _pause: ImageVector? = null

    val Stop: ImageVector
        get() = _stop ?: materialIcon(name = "Filled.Stop") {
            materialPath {
                moveTo(6.0f, 6.0f)
                horizontalLineToRelative(12.0f)
                verticalLineToRelative(12.0f)
                horizontalLineTo(6.0f)
                close()
            }
        }.also { _stop = it }
    private var _stop: ImageVector? = null

    val Replay15: ImageVector
        get() = _replay15 ?: materialIcon(name = "Filled.Replay15") {
            materialPath {
                moveTo(12.0f, 5.0f)
                verticalLineTo(1.0f)
                lineTo(7.0f, 6.0f)
                lineToRelative(5.0f, 5.0f)
                verticalLineTo(7.0f)
                curveToRelative(3.31f, 0.0f, 6.0f, 2.69f, 6.0f, 6.0f)
                reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f)
                reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f)
                horizontalLineTo(4.0f)
                curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f)
                reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f)
                reflectiveCurveToRelative(-3.58f, -8.0f, -8.0f, -8.0f)
                close()
            }
        }.also { _replay15 = it }
    private var _replay15: ImageVector? = null

    val Forward15: ImageVector
        get() = _forward15 ?: materialIcon(name = "Filled.Forward15") {
            materialPath {
                moveTo(12.0f, 5.0f)
                verticalLineTo(1.0f)
                lineToRelative(5.0f, 5.0f)
                lineToRelative(-5.0f, 5.0f)
                verticalLineTo(7.0f)
                curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f)
                reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f)
                reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f)
                horizontalLineToRelative(2.0f)
                curveToRelative(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f)
                reflectiveCurveToRelative(-8.0f, -3.58f, -8.0f, -8.0f)
                reflectiveCurveToRelative(3.58f, -8.0f, 8.0f, -8.0f)
                close()
            }
        }.also { _forward15 = it }
    private var _forward15: ImageVector? = null

    val Share: ImageVector
        get() = _share ?: materialIcon(name = "Filled.Share") {
            materialPath {
                moveTo(18.0f, 16.08f)
                curveToRelative(-0.76f, 0.0f, -1.44f, 0.3f, -1.96f, 0.77f)
                lineTo(8.91f, 12.7f)
                curveToRelative(0.05f, -0.23f, 0.09f, -0.46f, 0.09f, -0.7f)
                reflectiveCurveToRelative(-0.04f, -0.47f, -0.09f, -0.7f)
                lineToRelative(7.05f, -4.11f)
                curveToRelative(0.54f, 0.5f, 1.25f, 0.81f, 2.04f, 0.81f)
                curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f)
                reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f)
                reflectiveCurveToRelative(-3.0f, 1.34f, -3.0f, 3.0f)
                curveToRelative(0.0f, 0.24f, 0.04f, 0.47f, 0.09f, 0.7f)
                lineTo(8.04f, 9.81f)
                curveTo(7.5f, 9.31f, 6.79f, 9.0f, 6.0f, 9.0f)
                curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f)
                reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f)
                curveToRelative(0.79f, 0.0f, 1.5f, -0.31f, 2.04f, -0.81f)
                lineToRelative(7.12f, 4.16f)
                curveToRelative(-0.05f, 0.21f, -0.08f, 0.43f, -0.08f, 0.65f)
                curveToRelative(0.0f, 1.61f, 1.31f, 2.92f, 2.92f, 2.92f)
                reflectiveCurveToRelative(2.92f, -1.31f, 2.92f, -2.92f)
                curveToRelative(0.0f, -1.61f, -1.31f, -2.92f, -2.92f, -2.92f)
                close()
            }
        }.also { _share = it }
    private var _share: ImageVector? = null

    val MoreHoriz: ImageVector
        get() = _moreHoriz ?: materialIcon(name = "Filled.MoreHoriz") {
            materialPath {
                moveTo(6.0f, 10.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f)
                reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f)
                reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(12.0f, 10.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f)
                reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f)
                reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f)
                close()
                moveTo(18.0f, 10.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f)
                reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f)
                reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f)
                close()
            }
        }.also { _moreHoriz = it }
    private var _moreHoriz: ImageVector? = null

    val DeleteOutline: ImageVector
        get() = _deleteOutline ?: materialIcon(name = "Filled.DeleteOutline") {
            materialPath {
                moveTo(6.0f, 19.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(8.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(7.0f)
                horizontalLineTo(6.0f)
                verticalLineToRelative(12.0f)
                close()
                moveTo(8.0f, 9.0f)
                horizontalLineToRelative(8.0f)
                verticalLineToRelative(10.0f)
                horizontalLineTo(8.0f)
                verticalLineTo(9.0f)
                close()
                moveTo(15.5f, 4.0f)
                lineToRelative(-1.0f, -1.0f)
                horizontalLineToRelative(-5.0f)
                lineToRelative(-1.0f, 1.0f)
                horizontalLineTo(5.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(14.0f)
                verticalLineTo(4.0f)
                horizontalLineToRelative(-3.5f)
                close()
            }
        }.also { _deleteOutline = it }
    private var _deleteOutline: ImageVector? = null
}
