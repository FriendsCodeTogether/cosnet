Write-Output "Installing dotnet ef tools."
dotnet tool install -g dotnet-ef --version 5.0.0

Write-Output "`nCleaning CosNet.IDP"
dotnet clean --verbosity m
Write-Output "`nBuilding CosNet.IDP"
dotnet build --verbosity m


Write-Output "`nDeleting existing CosNet IDP DbContexts:"

Write-Output "`nDeleting ApplicationDbContext"
dotnet ef database drop -c ApplicationDbContext --force --no-build

Write-Output "`nDeleting PersistedGrantDbContext"
dotnet ef database drop -c PersistedGrantDbContext --force --no-build

Write-Output "`nDeleting ConfigurationDbContext"
dotnet ef database drop -c ConfigurationDbContext --force --no-build


Write-Output "`nMigrating CosNet IDP DbContexts:"

Write-Output "`nMigrating ApplicationDbContext"
dotnet ef database update -c ApplicationDbContext --no-build

Write-Output "`nMigrating PersistedGrantDbContext"
dotnet ef database update -c PersistedGrantDbContext --no-build

Write-Output "`nMigrating ConfigurationDbContext"
dotnet ef database update -c ConfigurationDbContext --no-build


Write-Output "`nSeeding CosNet IDP DbContexts:"
dotnet run /seed --no-build
