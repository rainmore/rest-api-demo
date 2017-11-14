package net.rainmore.modules.accounts

import net.rainmore.domains.accounts.Account
import net.rainmore.modules.BaseRepository

trait AccountRepository extends BaseRepository[Account, java.lang.Long]