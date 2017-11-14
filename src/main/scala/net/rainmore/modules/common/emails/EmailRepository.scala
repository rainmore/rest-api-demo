package net.rainmore.modules.common.emails

import net.rainmore.domains.common.emails.Email
import net.rainmore.modules.BaseRepository

trait EmailRepository extends BaseRepository[Email, java.lang.Long]
