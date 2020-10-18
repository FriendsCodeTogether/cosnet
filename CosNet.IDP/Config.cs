// Copyright (c) Brock Allen & Dominick Baier. All rights reserved.
// Licensed under the Apache License, Version 2.0. See LICENSE in the project root for license information.


using IdentityServer4;
using IdentityServer4.Models;
using Microsoft.Extensions.Configuration;
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
         new ApiScope[] { };

      public static IEnumerable<ApiResource> ApiResources =>
         new ApiResource[]
         {
            new ApiResource(
               "cosnet-api",
               "CosNet API",
               new List<string>() { "role" })
            {
               Scopes = { "cosnet-api"},

               // temp for ref token
               ApiSecrets = { new Secret("apisecret".Sha256()) }
            }
         };

      public static IEnumerable<Client> Clients =>
         new Client[]
         {
            new Client
            {
               ClientId = "cosnet-webui",
               ClientSecrets = { new Secret("49C1A7E1-0C79-4A89-A3D6-A37998FB86B0".Sha256()) },

               AllowedGrantTypes = GrantTypes.Code,

               AllowedCorsOrigins = { $"{ConfigurationManager.AppSettings["CosNetWebUIUrl"]}" },
               RedirectUris = { $"{ConfigurationManager.AppSettings["CosNetWebUIUrl"]}/signin-oidc" },
               FrontChannelLogoutUri = $"{ConfigurationManager.AppSettings["CosNetWebUIUrl"]}/signout-oidc",
               PostLogoutRedirectUris = { $"{ConfigurationManager.AppSettings["CosNetWebUIUrl"]}/signout-callback-oidc" },

               AllowOfflineAccess = true,
               AllowedScopes =
               {
                  IdentityServerConstants.StandardScopes.OpenId,
                  IdentityServerConstants.StandardScopes.Profile,
                  "cosnet-api"
               }
            },
         };
   }
}
