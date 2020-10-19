// Copyright (c) Brock Allen & Dominick Baier. All rights reserved.
// Licensed under the Apache License, Version 2.0. See LICENSE in the project root for license information.


using IdentityServer4;
using IdentityServer4.Models;
using System.Collections.Generic;
using System.Configuration;

namespace CosNet.IDP
{
   public static class Config
   {
      public static IEnumerable<IdentityResource> IdentityResources =>
         new IdentityResource[]
         {
            new IdentityResources.OpenId(),
            new IdentityResources.Profile(),
         };

      public static IEnumerable<ApiScope> ApiScopes =>
         new ApiScope[]
         {
            new ApiScope("cosnetapi")
         };

      public static IEnumerable<ApiResource> ApiResources =>
         new ApiResource[]
         {
            new ApiResource(
               "cosnetapi",
               "CosNet API",
               new List<string>() { "role" })
            {
               Scopes = { "cosnetapi"},

               // temp for ref token
               ApiSecrets = { new Secret("apisecret".Sha256()) }
            }
         };

      public static IEnumerable<Client> Clients =>
         new Client[]
         {
            new Client
            {
               ClientId = "cosnetwebui",
               AllowedGrantTypes = GrantTypes.Code,
               RequireClientSecret = false,
               AllowedCorsOrigins = { System.Environment.GetEnvironmentVariable("COSNET_WEBUI_URL") },
               RedirectUris = { $"{System.Environment.GetEnvironmentVariable("COSNET_WEBUI_URL")}/authentication/login-callback" },
               FrontChannelLogoutUri = $"{System.Environment.GetEnvironmentVariable("COSNET_WEBUI_URL")}/",
               PostLogoutRedirectUris = { $"{System.Environment.GetEnvironmentVariable("COSNET_WEBUI_URL")}/" },

               AllowOfflineAccess = true,
               AllowedScopes =
               {
                  IdentityServerConstants.StandardScopes.OpenId,
                  IdentityServerConstants.StandardScopes.Profile,
                  "cosnetapi"
               }
            },
         };
   }
}
