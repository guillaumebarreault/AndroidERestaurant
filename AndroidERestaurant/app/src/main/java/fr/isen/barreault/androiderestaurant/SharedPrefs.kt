package fr.isen.barreault.androiderestaurant

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class CipherSharedPrefs() {

    private lateinit var sharedPreferences: SharedPreferences

    private fun initEncryptedPreferences(context: Context){
        val masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
            "IdUserSave",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun putEncryptedPrefs(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getEncryptedPrefs(key: String): String? {

        return sharedPreferences.getString(key, "")
    }

    companion object {
        private var oneInstance: CipherSharedPrefs? = null
        fun getInstance(context: Context): CipherSharedPrefs {
            if(oneInstance==null) {
                oneInstance = CipherSharedPrefs()
                oneInstance?.initEncryptedPreferences(context)
            }
        return oneInstance as CipherSharedPrefs
        }
    }

}