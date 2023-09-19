package com.dafinrs.tixcompose.ui.pages.ticket


import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dafinrs.tixcompose.R

@Composable
fun ComponentTabRow(indexTabRow: Int, onTabClick: (Int) -> Unit) {
    TabRow(selectedTabIndex = indexTabRow) {
        Tab(
            selected = indexTabRow == 0,
            text = {
                Text(text = stringResource(id = R.string.tab_active_ticket))
            },
            onClick = {
                onTabClick(0)
            },
        )
        Tab(
            selected = indexTabRow == 1,
            text = {
                Text(text = stringResource(id = R.string.tab_list_transaction))
            },
            onClick = {
                onTabClick(1)
            },
        )
    }
}