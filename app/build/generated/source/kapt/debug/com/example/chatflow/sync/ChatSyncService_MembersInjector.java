package com.example.chatflow.sync;

import com.example.chatflow.domain.usecase.SyncChatsOnceUseCase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ChatSyncService_MembersInjector implements MembersInjector<ChatSyncService> {
  private final Provider<SyncChatsOnceUseCase> syncOnceProvider;

  public ChatSyncService_MembersInjector(Provider<SyncChatsOnceUseCase> syncOnceProvider) {
    this.syncOnceProvider = syncOnceProvider;
  }

  public static MembersInjector<ChatSyncService> create(
      Provider<SyncChatsOnceUseCase> syncOnceProvider) {
    return new ChatSyncService_MembersInjector(syncOnceProvider);
  }

  @Override
  public void injectMembers(ChatSyncService instance) {
    injectSyncOnce(instance, syncOnceProvider.get());
  }

  @InjectedFieldSignature("com.example.chatflow.sync.ChatSyncService.syncOnce")
  public static void injectSyncOnce(ChatSyncService instance, SyncChatsOnceUseCase syncOnce) {
    instance.syncOnce = syncOnce;
  }
}
