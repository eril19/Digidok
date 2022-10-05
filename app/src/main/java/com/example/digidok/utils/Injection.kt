package com.example.digidok.utils

import android.content.Context
import androidx.appcompat.widget.ThemedSpinnerAdapter
import com.example.digidok.data.RemoteDataSource
import com.example.digidok.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(RemoteDataSource)
    }

}