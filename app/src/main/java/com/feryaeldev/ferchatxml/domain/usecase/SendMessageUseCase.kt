package com.feryaeldev.ferchatxml.domain.usecase

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import com.feryaeldev.ferchatxml.MyApp
import com.feryaeldev.ferchatxml.data.remote.entity.dto.MessageDto
import com.feryaeldev.ferchatxml.data.remote.entity.dto.UserDto
import com.feryaeldev.ferchatxml.domain.repository.RemoteDatabaseRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val remoteDatabaseRepository: RemoteDatabaseRepository) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(message: String, username: String) {
        val epochMillis = MyApp.trustedTime?.computeCurrentUnixEpochMillis()

        val calendar = Calendar.getInstance().apply {
            timeInMillis = epochMillis ?: System.currentTimeMillis()
        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH) + 1

        val userDto = UserDto(userName = username, admin = false)
        val messageDto = MessageDto(
            msg = message,
            hour = "%02d:%02d".format(hour, min),
            date = "$day/$month/$year",
            user = userDto
        )

        remoteDatabaseRepository.sendMsg(messageDto)
    }
}