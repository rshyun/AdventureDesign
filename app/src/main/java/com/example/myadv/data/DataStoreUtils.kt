package com.example.myadv.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// 데이터 스토어 초기화
val Context.dataStore by preferencesDataStore(name = "settings")

// 키 정의
val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")

// 최초 실행 여부 확인 함수
fun Context.isFirstLaunch(): Boolean {
    return runBlocking {
        val preferences = dataStore.data.first()
        preferences[IS_FIRST_LAUNCH_KEY] ?: true // 기본값: true
    }
}

// 최초 실행 여부 업데이트 함수
suspend fun Context.setFirstLaunch(isFirstLaunch: Boolean) {
    dataStore.edit { preferences ->
        preferences[IS_FIRST_LAUNCH_KEY] = isFirstLaunch
    }
}

// 키 정의
val QUOKKA_NAME_KEY = stringPreferencesKey("quokka_name")

// 쿼카 이름 가져오기 함수
fun Context.getQuokkaName(): String {
    return runBlocking {
        val preferences = dataStore.data.first()
        preferences[QUOKKA_NAME_KEY] ?: "???"
    }
}

// 쿼카 이름 저장 함수
suspend fun Context.setQuokkaName(newName: String) {
    dataStore.edit { preferences ->
        preferences[QUOKKA_NAME_KEY] = newName
    }
}