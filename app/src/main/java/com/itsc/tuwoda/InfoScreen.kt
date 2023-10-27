package com.itsc.tuwoda

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.itsc.tuwoda.ui.theme.BlueLightAlfa90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    model: MyViewModel,
    office: Office,
    navController: NavHostController
) {
    val officeInfo = listOf<String>(
        "Aдресс отделения:\n" + model.curOffice.address,
        "Время работы для юридических лиц:\n" +
                if (model.curOffice.openHours.toString() == "null") "Нет"
                    else model.curOffice.openHours.toString(),
        "Время работы для физических лиц:\n" +
                if (model.curOffice.openHoursIndividual.toString() == "null") "Нет"
                    else model.curOffice.openHoursIndividual.toString(),
        "Тип:\n" +
                if (model.curOffice.salePointFormat.toString() == "null") "Нет"
                    else model.curOffice.salePointFormat.toString(),
        "Обслуживание с привелегиями:\n" +
                if (model.curOffice.officeType.toString() == "null") "Нет"
                    else model.curOffice.officeType.toString(),
        "Наличие пандуса:\n" +
                if (model.curOffice.kep.toString() == "null") "Нет"
                    else model.curOffice.kep.toString(),
        "Наличе РКО:\n" +
                if (model.curOffice.rko.toString() == "null") "Нет"
                    else model.curOffice.rko.toString(),
    )
    Scaffold(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.backinfo),
                contentScale = ContentScale.Crop
            ),
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = model.curOffice.name.toString(),
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BlueLightAlfa90
                )
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            itemsIndexed(officeInfo) { _, item ->
                Divider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier
                        .padding(10.dp, 5.dp)
                )
                androidx.compose.material3.TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 7.dp
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueLightAlfa90,
                        contentColor = Color.Black,
                        disabledContainerColor = BlueLightAlfa90,
                        disabledContentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = item,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}