package com.itsc.tuwoda.ui.theme

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.itsc.tuwoda.MyViewModel
import com.itsc.tuwoda.R
import com.itsc.tuwoda.allAtms
import com.itsc.tuwoda.allOffices

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyBottomSheetScaffold(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    scaffoldState: BottomSheetScaffoldState,
    taxi: () -> Unit,
    state: String,
    content: @Composable (PaddingValues) -> Unit,
    model: MyViewModel,
    navController: NavHostController
    ) {
    BottomSheetScaffold(
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 110.dp,
        sheetBackgroundColor = Color.Transparent,
        sheetContent = {
            Scaffold(
                modifier = Modifier
                    .paint(
                        painterResource(id = R.drawable.back),
                        contentScale = ContentScale.Crop
                    ),
                containerColor = Color.Transparent,
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .offset(y = (-45).dp),
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.Transparent,
                            navigationIconContentColor = Color.Transparent,
                            titleContentColor = Color.Transparent,
                            actionIconContentColor = Color.Transparent
                        ),
                        title = {
                            Icon(
                                painter = painterResource(id = R.drawable.divider),
                                contentDescription = "divider",
                                modifier = Modifier
                                    .width(70.dp),
                                tint = colorScheme.inverseOnSurface,
                            )
                        },
                    )
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateTopPadding() * 0 + 30.dp
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            var text1 by remember { mutableStateOf(TextFieldValue()) }
                            TextField(
                                value = text1,
                                onValueChange = { text1 = it },
                                placeholder = {
                                        Text(
                                            text = "Город, улица...",
                                            color = Color.Gray
                                        )
                                    },
                                modifier = Modifier
                                    .weight(4f)
                                    .height(50.dp)
                                    .padding(end = 5.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color(0xFFF1F2F4),
                                    textColor = Color.Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                ),
                                shape = RoundedCornerShape(percent = 10)
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFF1F2F4)),
                                onClick = {
                                    model.stateSerch = !model.stateSerch
                                },
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.MoreVert,
                                        contentDescription = "morevert",
                                        tint = Color.Black,
                                    )
                                }
                            )
                            IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFF1F2F4)),
                                onClick = {},
                                content = {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = "map",
                                        tint = Color.Black,
                                    )
                                }
                            )
                        }

                        val rull = listOf<String>(
                            "Работает сейчас",
                            "Минимальная загруженность",
                            "Ближе всего",
                            "Наличие пандуса",
                            "Рядом с метро"
                        )

                        if (model.stateSerch){
                            LazyVerticalGrid(
                                modifier = Modifier.fillMaxWidth(),
                                columns = GridCells.Adaptive(130.dp),
                                content = {
                                    itemsIndexed(rull){ ind, item ->
                                        OutlinedButton(
                                            onClick = {
                                                model.stateButton[ind].value = !model.stateButton[ind].value

                                                      },
                                            modifier = Modifier
                                                .size(width = 80.dp, height = 57.dp)
                                                .padding(2.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor =
                                                if (model.stateButton[ind].value) Blue
                                                        else Color.White,
                                                contentColor =
                                                if (model.stateButton[ind].value) Color.White
                                                else Blue,
                                            ),
                                            shape = RoundedCornerShape(20.dp),
                                        ) {
                                            Text(
                                                text = item,
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedButton(
                                shape = RoundedCornerShape(20.dp),
                                colors = if(model.stateAtmOrOffice)
                                    ButtonDefaults.buttonColors(Color.White)
                                else
                                    ButtonDefaults.buttonColors(Blue),
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 5.dp)
                                    .height(50.dp),
                                onClick = {
                                    model.stateAtmOrOffice = false
                                },
                            ) {
                                Text(
                                    text = "Отделения",
                                    color = if(model.stateAtmOrOffice)
                                        Blue
                                    else
                                        Color.White,
                                    overflow = TextOverflow.Visible
                                )
                            }
                            OutlinedButton(
                                shape = RoundedCornerShape(20.dp),
                                colors = if(model.stateAtmOrOffice)
                                    ButtonDefaults.buttonColors(Blue)
                                else
                                    ButtonDefaults.buttonColors(Color.White),
                                modifier = Modifier
                                    .weight(2f)
                                    .padding(end = 5.dp)
                                    .height(50.dp),
                                onClick = {
                                    model.stateAtmOrOffice = true
                                },
                            ) {
                                Text(
                                    text = "Банкоматы",
                                    color = if(model.stateAtmOrOffice)
                                        Color.White
                                    else
                                        Blue,
                                    overflow = TextOverflow.Visible
                                )
                            }
                            /*IconButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Blue),
                                onClick = {},
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.filter_by_near_empty_bank),
                                        contentDescription = "map",
                                        tint = Color.White,
                                    )
                                }
                            )*/
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            itemsIndexed(
                                if (model.stateAtmOrOffice) allAtms
                                else allOffices
                            ) { _, item ->
                            Divider(
                                thickness = 1.dp,
                                color = Color.LightGray,
                                modifier = Modifier
                                    .padding(10.dp, 5.dp)
                            )
                            TextButton(
                                onClick = {
                                    model.curOffice = item
                                    navController.navigate("more")
                                          },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 7.dp
                                    )
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = BlueLightAlfa90,
                                    contentColor = Color.Black,
                                    disabledContainerColor = BlueLightAlfa90,
                                    disabledContentColor = Color.Black
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Image(
                                        painter = painterResource( id =
                                        if (model.stateAtmOrOffice) R.drawable.icatm
                                        else R.drawable.icoffices
                                        ),
                                        contentDescription = "icoffices",
                                        modifier = Modifier
                                            .weight(2f)
                                            .padding(end = 5.dp)
                                            .clip(RoundedCornerShape(45.dp))
                                    )
                                    Text(
                                        text = item.address,
                                        modifier = Modifier
                                            .weight(8f)
                                            .padding(horizontal = 5.dp)
                                    )
                                    Text(
                                        text = "${item.distance} м",
                                        modifier = Modifier
                                            .weight(3f)
                                            .padding(horizontal = 5.dp)
                                    )
                                    IconButton(
                                        onClick = taxi,
                                        modifier = Modifier
                                            .weight(2f)
                                            .padding(start = 10.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.taxi),
                                            contentDescription = "taxi"
                                        )
                                    }

                                }
                            }

                        }
                    }

                    }
                }
            }
        },
        sheetShape = RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),

        content = content
    )

}