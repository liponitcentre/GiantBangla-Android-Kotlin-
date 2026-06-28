package com.example.giantbangla.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giantbangla.home.HomeScreen
import com.example.giantbangla.onboarding.OnboardingOne
import com.example.giantbangla.onboarding.OnboardingTwo

/** Route names for the auth flow. */
object AuthRoutes {
    const val OnboardingOne = "onboarding_one"
    const val OnboardingTwo = "onboarding_two"
    const val Splash = "splash"
    const val PhoneSignUp = "phone_signup"
    const val Otp = "otp/{phone}"
    const val HomeScreen = "home_screen}"

    fun otp(phone: String) = "otp/$phone"
}

@Composable
fun AuthNavGraph(navController: NavHostController = rememberNavController()) {
    // First screen of the app:
    // NavHost(navController = navController, startDestination = AuthRoutes.OnboardingOne)
    NavHost(navController = navController, startDestination = AuthRoutes.HomeScreen)
    {
        composable(AuthRoutes.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(AuthRoutes.PhoneSignUp) {
                        popUpTo(AuthRoutes.Splash) { inclusive = true }
                    }
                },
            )
        }
        composable(AuthRoutes.OnboardingOne) {
            // onNext = advance to the second onboarding screen.
            OnboardingOne(
                onTimeout = {
                    navController.navigate(AuthRoutes.OnboardingTwo) {
                        popUpTo(AuthRoutes.OnboardingOne) { inclusive = true }
                    }
                },
            )
        }

        composable(AuthRoutes.OnboardingTwo) {
            // onNext = finish onboarding, go to Splash and clear onboarding from back stack.
            OnboardingTwo(
                onTimeout = {
                    navController.navigate(AuthRoutes.PhoneSignUp) {
                        popUpTo(AuthRoutes.OnboardingTwo) { inclusive = true }
                    }
                },
            )
        }



        composable(AuthRoutes.PhoneSignUp) {
            PhoneSignUpScreen(
                onSubmit = { phone -> navController.navigate(AuthRoutes.otp(phone)) },
                onLoginClick = { /* navigate to login */ },
            )
        }

        composable(AuthRoutes.Otp) { backStackEntry ->
            val phone = backStackEntry.arguments?.getString("phone").orEmpty()
            OtpVerificationScreen(
                phoneNumber = phone,
                onVerify = {
                    navController.navigate(AuthRoutes.HomeScreen) {
                        popUpTo(AuthRoutes.Otp) { inclusive = true }
                    }
                },
                onResend = { /* trigger resend */ },
            )
        }


        composable(AuthRoutes.HomeScreen) {
            HomeScreen(

            )
        }



    }
}
