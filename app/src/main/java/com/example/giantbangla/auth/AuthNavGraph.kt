package com.example.giantbangla.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.giantbangla.onboarding.OnboardingOne
import com.example.giantbangla.onboarding.OnboardingTwo

/** Route names for the auth flow. */
object AuthRoutes {
    const val OnboardingOne = "onboarding_one"
    const val OnboardingTwo = "onboarding_two"
    const val Splash = "splash"
    const val PhoneSignUp = "phone_signup"
    const val Otp = "otp/{phone}"

    fun otp(phone: String) = "otp/$phone"
}

@Composable
fun AuthNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = AuthRoutes.Splash) {

        composable(AuthRoutes.OnboardingOne) {
            OnboardingOne(
                onTimeout = {
                    navController.navigate(AuthRoutes.OnboardingOne) {
                        popUpTo(AuthRoutes.OnboardingOne) { inclusive = true }
                    }
                },
            )
        }
        composable(AuthRoutes.OnboardingTwo) {
            OnboardingTwo(
                onTimeout = {
                    navController.navigate(AuthRoutes.OnboardingTwo) {
                        popUpTo(AuthRoutes.OnboardingTwo) { inclusive = true }
                    }
                },
            )
        }
        composable(AuthRoutes.Splash) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(AuthRoutes.PhoneSignUp) {
                        popUpTo(AuthRoutes.Splash) { inclusive = true }
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
                onVerify = { /* verify + navigate to home */ },
                onResend = { /* trigger resend */ },
            )
        }
    }
}
