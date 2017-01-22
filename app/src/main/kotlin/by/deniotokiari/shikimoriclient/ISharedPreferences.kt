package by.deniotokiari.shikimoriclient

import android.content.Context

interface ISharedPreferences {

    fun get(key: String, defaultValue: String = ""): String

    fun put(key: String, value: String)

    private object Constants {
        const val APP_PREFERENCES: String = "APP_PREFERENCES"
        const val APP_PREFERENCES_MODE: Int = Context.MODE_PRIVATE
    }

    companion object {

        fun newInstance(context: Context): ISharedPreferences {
            val sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Constants.APP_PREFERENCES_MODE)

            return object : ISharedPreferences {
                override fun get(key: String, defaultValue: String): String {
                    return sharedPreferences.getString(key, defaultValue)
                }

                override fun put(key: String, value: String) {
                    sharedPreferences
                            .edit()
                            .putString(key, value)
                            .apply()
                }
            }
        }

    }

}