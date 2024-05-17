package com.salesforce.cdp.queryservice.auth;

import com.salesforce.cdp.queryservice.auth.jwt.JwtCoreTokenProvider;
import com.salesforce.cdp.queryservice.auth.jwt.JwtLoginClient;
import com.salesforce.cdp.queryservice.auth.refresh.CoreTokenFromRefreshTokenProvider;
import com.salesforce.cdp.queryservice.auth.refresh.RefreshTokenClient;
import com.salesforce.cdp.queryservice.auth.unpwd.UnPwdAuthClient;
import com.salesforce.cdp.queryservice.auth.unpwd.UnPwdTokenProvider;
import com.salesforce.cdp.queryservice.util.Constants;
import com.salesforce.cdp.queryservice.util.PropertyUtils;
import com.salesforce.cdp.queryservice.util.TokenException;
import okhttp3.OkHttpClient;

import java.util.Properties;

public class CoreTokenProviderFactory {

    public static CoreTokenProvider getCoreTokenProvider(Properties properties, OkHttpClient client) throws TokenException {
        if (canSupportUsernamePasswordProvider(properties)) {
            return new UnPwdTokenProvider(properties, new UnPwdAuthClient(client));
        } else if (canSupportJwtProvider(properties)) {
            return new JwtCoreTokenProvider(properties, new JwtLoginClient(client));
        } else if (canSupportRefreshToken(properties)) {
            return new CoreTokenFromRefreshTokenProvider(properties, new RefreshTokenClient(client),
                    new TokenExchangeHelper(properties, client));
        }
        throw new TokenException("Sufficient properties for deciding auth flow is not provided");
    }

    private static boolean canSupportUsernamePasswordProvider(Properties properties) {
        return PropertyUtils.isPropertyNonEmpty(properties, Constants.USER_NAME)
                && PropertyUtils.isPropertyNonEmpty(properties, Constants.PD);
    }

    private static boolean canSupportJwtProvider(Properties properties) {
        return PropertyUtils.isPropertyNonEmpty(properties, Constants.USER_NAME)
                && PropertyUtils.isPropertyNonEmpty(properties, Constants.PRIVATE_KEY);
    }

    private static boolean canSupportRefreshToken(Properties properties) {
        return PropertyUtils.isPropertyNonEmpty(properties, Constants.REFRESHTOKEN);
    }
}