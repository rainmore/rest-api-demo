package net.rainmore.modules.common.addresses

import javax.inject.Inject

import net.rainmore.domains.common.addresses.Address
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddressService @Inject()
(
    addressRepository: AddressRepository
) {

    def exists(id: Long): Boolean = addressRepository.exists(id)

    def findOne(id: Long): Address = addressRepository.findOne(id)

    @Transactional
    def save(address: Address): Address = addressRepository.save(address)

    @Transactional
    def delete(address: Address): Unit = addressRepository.delete(address)

}
